package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.DocumentController;
import by.triumgroup.recourse.controller.exception.NotFoundException;
import by.triumgroup.recourse.document.DocumentType;
import by.triumgroup.recourse.document.generator.DocumentGenerator;
import by.triumgroup.recourse.document.model.provider.ContentProvider;
import by.triumgroup.recourse.document.model.provider.impl.CourseLessonsContentProvider;
import by.triumgroup.recourse.document.model.provider.impl.CourseStudentsContentProvider;
import by.triumgroup.recourse.document.model.provider.impl.StudentProfileContentProvider;
import by.triumgroup.recourse.entity.dto.CourseWithStudents;
import by.triumgroup.recourse.entity.dto.StudentProfile;
import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.entity.support.DocumentTypeEnumConverter;
import by.triumgroup.recourse.service.CourseService;
import by.triumgroup.recourse.service.LessonService;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.util.DocumentGeneratorCallWrapper;
import org.slf4j.Logger;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static by.triumgroup.recourse.util.ServiceCallWrapper.wrapServiceCall;
import static by.triumgroup.recourse.util.Util.allItemsPage;
import static by.triumgroup.recourse.util.Util.sanitizeFilename;
import static org.slf4j.LoggerFactory.getLogger;


public class DocumentControllerImpl implements DocumentController {

    private static final Logger logger = getLogger(DocumentControllerImpl.class);
    private UserService userService;
    private CourseService courseService;
    private LessonService lessonService;

    public DocumentControllerImpl(UserService userService,
                                  CourseService courseService,
                                  LessonService lessonService) {
        this.userService = userService;
        this.courseService = courseService;
        this.lessonService = lessonService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(DocumentType.class, new DocumentTypeEnumConverter());
    }

    @Override
    public void exportStudentProfile(
            @PathVariable("id") Integer id,
            @RequestParam("type") DocumentType documentType,
            HttpServletResponse response) {
        Optional<StudentProfile> studentOptional = wrapServiceCall(logger, () -> userService.getStudent(id));
        if (studentOptional.isPresent()){
            StudentProfile existingStudentProfile = studentOptional.get();
            dispatchDocumentRequest(
                    response,
                    documentType,
                    existingStudentProfile,
                    existingStudentProfile.getAverageMarks(),
                    new StudentProfileContentProvider());
        } else {
            throw new NotFoundException();
        }

    }

    @Override
    public void exportCourseLessons(@PathVariable("id") Integer id,
                                    @RequestParam("type") DocumentType documentType,
                                    HttpServletResponse response) {
        Optional<Course> courseOptional = wrapServiceCall(logger, () -> courseService.findById(id));
        if (courseOptional.isPresent()){
            Course existingCourse = courseOptional.get();
            List<Lesson> courseLessons = lessonService.findByCourseId(id, allItemsPage()).get();
            dispatchDocumentRequest(
                    response,
                    documentType,
                    existingCourse,
                    courseLessons,
                    new CourseLessonsContentProvider());
        } else {
            throw new NotFoundException();
        }

    }

    @Override
    public void exportCourseStudents(@PathVariable("id") Integer id,
                                     @RequestParam("type") DocumentType documentType,
                                     HttpServletResponse response) {
        Optional<CourseWithStudents> courseProfileOptional = wrapServiceCall(logger, () -> courseService.getCourseWithStudents(id));
        if (courseProfileOptional.isPresent()){
            CourseWithStudents courseWithStudents = courseProfileOptional.get();
            dispatchDocumentRequest(
                    response,
                    documentType,
                    courseWithStudents,
                    courseWithStudents.getStudentsWithMarks(),
                    new CourseStudentsContentProvider());
        } else {
            throw new NotFoundException();
        }

    }

    private <TMainEntity, TTableEntity> void dispatchDocumentRequest(HttpServletResponse response,
                                                                     DocumentType documentType,
                                                                     TMainEntity mainEntity,
                                                                     Collection<TTableEntity> tableEntities,
                                                                     ContentProvider<TMainEntity, TTableEntity> contentProvider) {
        DocumentGenerator<TMainEntity, TTableEntity> documentGenerator = documentType.createGenerator(contentProvider);
        response.setContentType(documentGenerator.getContentType());
        if (documentGenerator.isForceAttachment()) {
            response.setHeader("Content-disposition", "attachment;filename=" + createFilename(mainEntity, contentProvider, documentGenerator));
        }
        DocumentGeneratorCallWrapper.wrapDocumentGeneratorCall(() -> documentGenerator.writeDocument(
                response,
                mainEntity,
                tableEntities));
    }

    private <TMainEntity, TTableEntity> String createFilename(TMainEntity mainEntity,
                                                              ContentProvider<TMainEntity, TTableEntity> contentProvider,
                                                              DocumentGenerator<TMainEntity, TTableEntity> documentGenerator) {
        String filename = String.format("%s.%s",
                contentProvider.createFilename(mainEntity),
                documentGenerator.getFileExtension());
        filename = sanitizeFilename(filename);
        return filename;
    }
}
