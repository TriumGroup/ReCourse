package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.TeacherController;
import by.triumgroup.recourse.entity.Course;
import by.triumgroup.recourse.entity.Lesson;
import by.triumgroup.recourse.entity.StudentReport;
import by.triumgroup.recourse.entity.TeacherFeedback;
import by.triumgroup.recourse.service.CourseService;
import by.triumgroup.recourse.service.LessonService;
import by.triumgroup.recourse.service.StudentReportService;
import by.triumgroup.recourse.service.TeacherFeedbackService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class TeacherControllerImpl implements TeacherController {

    private final CourseService courseService;
    private final LessonService lessonService;
    private final StudentReportService studentReportService;
    private final TeacherFeedbackService teacherFeedbackService;

    public TeacherControllerImpl(
            CourseService courseService,
            LessonService lessonService,
            StudentReportService studentReportService,
            TeacherFeedbackService teacherFeedbackService
    ) {
        this.courseService = courseService;
        this.lessonService = lessonService;
        this.studentReportService = studentReportService;
        this.teacherFeedbackService = teacherFeedbackService;
    }

    @Override
    public List<Course> getCourses(
            @PathVariable("teacherId") Integer teacherId,
            @RequestParam(value = "status", required = false) Course.Status status,
            Pageable pageable
    ) {
        List<Course> courses;
        if (status == null) {
            courses = courseService.findByTeacherId(teacherId, pageable);
        } else {
            courses = courseService.findByTeacherIdAndStatus(teacherId, status, pageable);
        }
        return courses;
    }

    @Override
    public List<Lesson> getLessons(
            @PathVariable("teacherId") Integer teacherId,
            @RequestParam(value = "courseId", required = false) Integer courseId,
            Pageable pageable
    ) {
        List<Lesson> lessons;
        if (courseId == null) {
            lessons = lessonService.findByTeacherId(teacherId, pageable);
        } else {
            lessons = lessonService.findByTeacherIdAndCourseId(teacherId, courseId, pageable);
        }
        return lessons;
    }

    @Override
    public List<StudentReport> getReports(@PathVariable("teacherId") Integer teacherId, Pageable pageable) {
        return studentReportService.findByTeacherId(teacherId, pageable);
    }

    @Override
    public List<TeacherFeedback> getFeedbacks(@PathVariable("teacherId") Integer teacherId, Pageable pageable) {
        return teacherFeedbackService.findByTeacherId(teacherId, pageable);
    }
}
