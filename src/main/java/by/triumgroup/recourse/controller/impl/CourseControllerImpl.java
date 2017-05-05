package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.configuration.security.Auth;
import by.triumgroup.recourse.configuration.security.UserAuthDetails;
import by.triumgroup.recourse.controller.CourseController;
import by.triumgroup.recourse.controller.exception.AccessDeniedException;
import by.triumgroup.recourse.controller.exception.BadRequestException;
import by.triumgroup.recourse.controller.exception.NotFoundException;
import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.CourseFeedback;
import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.entity.support.CourseStatusEnumConverter;
import by.triumgroup.recourse.service.CourseFeedbackService;
import by.triumgroup.recourse.service.CourseService;
import by.triumgroup.recourse.service.LessonService;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import static by.triumgroup.recourse.util.ServiceCallWrapper.wrapServiceCall;
import static org.slf4j.LoggerFactory.getLogger;

public class CourseControllerImpl
        extends AbstractCrudController<Course, Integer>
        implements CourseController {

    private static final Logger logger = getLogger(CourseControllerImpl.class);
    private final CourseService courseService;
    private final LessonService lessonService;
    private final CourseFeedbackService courseFeedbackService;

    public CourseControllerImpl(CourseService courseService,
                                LessonService lessonService,
                                CourseFeedbackService courseFeedbackService
    ) {
        super(courseService, logger);
        this.courseService = courseService;
        this.lessonService = lessonService;
        this.courseFeedbackService = courseFeedbackService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Course.Status.class,
                new CourseStatusEnumConverter());
    }

    @Override
    public Iterable<Course> getAll(@Auth UserAuthDetails authDetails, Pageable pageable) {
        if (authDetails.isStudent()) {
            return wrapServiceCall(logger, () -> {
                Optional<List<Course>> courses = courseService.findAllExcludingStatus(Course.Status.DRAFT, pageable);
                return courses.orElseThrow(NotFoundException::new);
            });
        } else {
            return super.getAll(authDetails, pageable);
        }
    }

    @Override
    public List<Lesson> getLessons(@PathVariable("courseId") Integer courseId, @Auth UserAuthDetails authDetails, Pageable pageable) {
        checkCourseVisibility(courseId, authDetails);
        return wrapServiceCall(logger, () -> {
            Optional<List<Lesson>> lessons = lessonService.findByCourseId(courseId, pageable);
            return lessons.orElseThrow(NotFoundException::new);
        });
    }

    private void checkCourseVisibility(Integer courseId, UserAuthDetails authDetails) {
        Optional<Course> course = wrapServiceCall(logger, () -> courseService.findById(courseId));
        if (course.isPresent() && course.get().getStatus() == Course.Status.DRAFT && authDetails.isStudent()) {
            throw new NotFoundException();
        }
    }

    @Override
    public List<CourseFeedback> getFeedbacks(@PathVariable("courseId") Integer courseId, @Auth UserAuthDetails authDetails, Pageable pageable) {
        checkCourseVisibility(courseId, authDetails);
        return wrapServiceCall(logger, () -> {
            Optional<List<CourseFeedback>> feedbacks = courseFeedbackService.findByCourseId(courseId, pageable);
            return feedbacks.orElseThrow(NotFoundException::new);
        });
    }

    @Override
    public List<User> getStudents(@PathVariable("courseId") Integer courseId, @Auth UserAuthDetails authDetails) {
        if (!authDetails.isStudent()) {
            return wrapServiceCall(logger, () -> courseService.findStudentsForCourse(courseId));
        } else {
            throw new AccessDeniedException();
        }
    }

    @Override
    public List<Course> searchByTitle(@RequestParam("title") String title, @Auth UserAuthDetails authDetails, Pageable pageable) {
        if (authDetails.isStudent()) {
            return wrapServiceCall(logger, () -> courseService.searchByTitle(title, pageable))
                    .orElseThrow(NotFoundException::new);
        } else {
            return wrapServiceCall(logger, () -> courseService.searchByTitleExcludingStatus(title, Course.Status.DRAFT, pageable))
                    .orElseThrow(NotFoundException::new);
        }
    }

    @Override
    public List<Course> searchByStatus(@RequestParam("status") Course.Status status, @Auth UserAuthDetails authDetails, Pageable pageable) {
        if (status == Course.Status.DRAFT && authDetails.isStudent()) {
            throw new BadRequestException("Error", "Invalid course type");
        }
        return wrapServiceCall(logger, () -> courseService.findByStatus(status, pageable))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Course> getAvailableForStudent(@PathVariable("studentId") Integer studentId, @Auth UserAuthDetails authDetails, Pageable pageable) {
        if (!authDetails.isAdmin() && !studentId.equals(authDetails.getId())) {
            throw new AccessDeniedException();
        }
        return wrapServiceCall(logger, () -> courseService.findAvailableForUser(studentId, pageable))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Course> getRegisteredForStudent(@PathVariable("studentId") Integer studentId, @Auth UserAuthDetails authDetails, Pageable pageable) {
        if (!authDetails.isAdmin() && !studentId.equals(authDetails.getId())) {
            throw new AccessDeniedException();
        }
        return wrapServiceCall(logger, () -> courseService.findRegisteredForUser(studentId, pageable))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    protected boolean hasAuthorityToEdit(Course entity, UserAuthDetails authDetails) {
        return authDetails.isAdmin();
    }

    public void registerToCourse(@PathVariable("courseId") Integer courseId, @Auth UserAuthDetails authDetails) {
        wrapServiceCall(logger, () -> courseService.registerStudentToCourse(courseId, authDetails.getId(), false));
    }

    @Override
    public void unregisterFromCourse(@PathVariable("courseId") Integer courseId, @Auth UserAuthDetails authDetails) {
        wrapServiceCall(logger, () -> courseService.removeStudentFromCourse(courseId, authDetails.getId(), false));
    }

    @Override
    public void registerStudentToCourse(@PathVariable("courseId") Integer courseId, @RequestParam("studentId") Integer studentId, @Auth UserAuthDetails authDetails) {
        checkAuthority(null, authDetails, this::hasAuthorityToEdit);
        wrapServiceCall(logger, () -> courseService.registerStudentToCourse(courseId, studentId, true));
    }

    @Override
    public void unregisterStudentFromCourse(@PathVariable("courseId") Integer courseId, @RequestParam("studentId") Integer studentId, @Auth UserAuthDetails authDetails) {
        checkAuthority(null, authDetails, this::hasAuthorityToEdit);
        wrapServiceCall(logger, () -> courseService.removeStudentFromCourse(courseId, studentId, true));
    }
}
