package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.entity.model.CourseFeedback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/courses/feedbacks")
public interface CourseFeedbackController extends CrudController<CourseFeedback, Integer> {

    @GetMapping("/course/{courseId}/student/{studentId}")
    CourseFeedback findByCourseIdAndStudentId(@PathVariable("courseId") Integer courseId, @PathVariable("studentId") Integer studentId);

}
