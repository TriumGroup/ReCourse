package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CourseController;
import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.CrudControllerTest;
import by.triumgroup.recourse.entity.model.BaseEntity;
import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.service.*;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.CourseSupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.LessonSupplier;
import org.mockito.Mockito;

public class CourseControllerTest<E extends BaseEntity<ID>, ID> extends CrudControllerTest<Course, Integer> {
    private final CourseService courseService;
    private final LessonService lessonService;
    private final CourseFeedbackService courseFeedbackService;
    private final StudentReportService studentReportService;
    private final CourseController courseController;
    private final CourseSupplier courseSupplier;
    private final LessonSupplier lessonSupplier;

    public CourseControllerTest() {
        courseService = Mockito.mock(CourseService.class);
        lessonService = Mockito.mock(LessonService.class);
        courseFeedbackService = Mockito.mock(CourseFeedbackService.class);
        studentReportService = Mockito.mock(StudentReportService.class);
        courseController = new CourseControllerImpl(courseService, lessonService, courseFeedbackService, studentReportService);
        courseSupplier = new CourseSupplier();
        lessonSupplier = new LessonSupplier();
    }

//    @Test
//    public void getLessonsNotExistingCourseTest() throws Exception{
////        when(lessonService.findByCourseId(any(), any())).thenReturn(Lists.newArrayList(lessonSupplier.getValidEntityWithId()));
////        sendGet("/course/%s/lessons", courseSupplier.getAnyId())
////                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void getLessonsExistingCourseTest() throws Exception{
////        when(lessonService.findByCourseId(any(), any())).thenReturn(Lists.newArrayList(lessonSupplier.getValidEntityWithId()));
////        sendGet("/course/%s/lessons", courseSupplier.getAnyId())
////                .andExpect(status().isNotFound());
//    }

    @Override
    protected CrudController<Course, Integer> getController() {
        return courseController;
    }

    @Override
    protected CrudService<Course, Integer> getService() {
        return courseService;
    }

    @Override
    protected String getEntityName() {
        return "course";
    }

    @Override
    protected EntitySupplier<Course, Integer> getEntitySupplier() {
        return courseSupplier;
    }
}
