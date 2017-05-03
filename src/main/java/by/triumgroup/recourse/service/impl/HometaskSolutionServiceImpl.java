package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import by.triumgroup.recourse.entity.dto.MarkedHometaskSolution;
import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.HometaskSolutionRepository;
import by.triumgroup.recourse.repository.LessonRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.HometaskSolutionService;
import by.triumgroup.recourse.service.exception.ServiceBadRequestException;
import by.triumgroup.recourse.service.exception.ServiceException;
import by.triumgroup.recourse.validation.support.UserFieldInfo;
import by.triumgroup.recourse.validation.validator.HometaskSolutionReferenceValidator;
import by.triumgroup.recourse.validation.validator.UserRoleValidator;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Validator;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACall;
import static by.triumgroup.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;
import static by.triumgroup.recourse.util.Util.ifExistsWithRole;
import static org.slf4j.LoggerFactory.getLogger;

public class HometaskSolutionServiceImpl
        extends AbstractCrudService<HometaskSolution, Integer>
        implements HometaskSolutionService {

    private static final Logger logger = getLogger(HometaskSolutionServiceImpl.class);
    private final HometaskSolutionRepository repository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private HometaskSolutionReferenceValidator hometaskSolutionReferenceValidator;
    private LessonRepository lessonRepository;

    public HometaskSolutionServiceImpl(HometaskSolutionRepository repository,
                                       UserRepository userRepository,
                                       LessonRepository lessonRepository,
                                       CourseRepository courseRepository,
                                       HometaskSolutionReferenceValidator hometaskSolutionReferenceValidator) {
        super(repository);
        this.repository = repository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
        this.hometaskSolutionReferenceValidator = hometaskSolutionReferenceValidator;
    }

    @Override
    public <S extends HometaskSolution> Optional<S> add(S entity) {
        Lesson lesson = wrapJPACall(() -> lessonRepository.findOne(entity.getLessonId()));
        User student = wrapJPACall(() -> userRepository.findOne(entity.getStudent().getId()));
        checkUserAndLessonExistence(student, lesson);
        checkCreationAvailability(lesson, student);
        return super.add(entity);
    }

    private void checkUserAndLessonExistence(User student, Lesson lesson) {
        List<ErrorMessage> messages = new ArrayList<>();
        if (lesson == null) {
            messages.add(new ErrorMessage("lessonId", "Lesson not found"));
        }
        if (student == null) {
            messages.add(new ErrorMessage("student", "Student not found"));
        }
        rejectIfNeed(messages);
    }


    private void checkCreationAvailability(Lesson lesson, User student) {
        Course course = wrapJPACall(() -> courseRepository.findOne(lesson.getCourseId()));
        List<ErrorMessage> messages = new ArrayList<>();
        if (course != null) {
            Set<User> registeredUsers = course.getStudents();
            if (!registeredUsers.contains(student)) {
                messages.add(new ErrorMessage("student", "Cannot add solution to lesson of unregistered course"));
            } else {
                if (lesson.getStartTime().before(Timestamp.from(Instant.now()))) {
                    messages.add(new ErrorMessage("lessonId", "Cannot add solutions to future lessons"));
                }
            }
        } else {
            messages.add(new ErrorMessage("Error", "Course of this lesson not found"));
        }
        rejectIfNeed(messages);
    }

    @Override
    public Optional<HometaskSolution> update(HometaskSolution entity, Integer id, boolean force) {
        if (!force) {
            throw new ServiceBadRequestException("Access Denied", "Cannot update solutions.");
        }
        return super.update(entity, id);
    }

    @Override
    public Optional<HometaskSolution> update(HometaskSolution entity, Integer id) {
        logger.warn("This method shouldn't be called.");
        throw new ServiceException();
    }

    @Override
    public Optional<List<HometaskSolution>> findByLessonId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (lessonRepository.exists(id))
                ? repository.findByLessonId(id, pageable)
                : null
        );
    }

    @Override
    public Optional<List<HometaskSolution>> findByStudentId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (ifExistsWithRole(userRepository, id, User.Role.STUDENT))
                ? repository.findByStudentId(id, pageable)
                : null
        );
    }

    @Override
    public Optional<HometaskSolution> findByStudentIdAndLessonId(Integer studentId, Integer lessonId) {
        return wrapJPACallToOptional(() -> (ifExistsWithRole(userRepository, studentId, User.Role.STUDENT) && lessonRepository.exists(lessonId))
                ? repository.findByStudentIdAndLessonId(studentId, lessonId)
                : null
        );
    }

    @Override
    public List<MarkedHometaskSolution> findAllMarked(Pageable pageable) {
        return wrapJPACall(() -> repository.findAllMarked(pageable));
    }

    @Override
    public List<MarkedHometaskSolution> findMarkedByStudentId(Integer id, Pageable pageable) {
        return wrapJPACall(() -> repository.findMarkedByStudentId(id, pageable));
    }

    @Override
    public List<MarkedHometaskSolution> findMarkedByLessonId(Integer id, Pageable pageable) {
        return wrapJPACall(() -> repository.findMarkedByLessonId(id, pageable));
    }

    private void rejectIfNeed(List<ErrorMessage> messages) {
        if (!messages.isEmpty()) {
            throw new ServiceBadRequestException(messages);
        }
    }

    @Override
    protected String getEntityName() {
        return "hometask solution";
    }

    @Override
    protected List<Validator> getValidators() {
        UserFieldInfo<HometaskSolution, Integer> studentFieldInfo = new UserFieldInfo<>(
                HometaskSolution::getStudent,
                "student",
                User.Role.STUDENT
        );
        return Arrays.asList(
                new UserRoleValidator<>(studentFieldInfo, userRepository, repository),
                hometaskSolutionReferenceValidator);
    }
}
