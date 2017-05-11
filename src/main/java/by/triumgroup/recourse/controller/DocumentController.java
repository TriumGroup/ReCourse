package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.document.DocumentType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public interface DocumentController {

    @GetMapping("/users/{id}/profile/export")
    void exportStudentProfile(
            @PathVariable("id") Integer id,
            @RequestParam("type") DocumentType documentType,
            HttpServletResponse response);

    @GetMapping("/courses/{id}/lessons/export")
    void exportCourseLessons(
            @PathVariable("id") Integer id,
            @RequestParam("type") DocumentType documentType,
            HttpServletResponse response);

    @GetMapping("/courses/{id}/students/export")
    void exportCourseStudents(
            @PathVariable("id") Integer id,
            @RequestParam("type") DocumentType documentType,
            HttpServletResponse response);

    @GetMapping("/courses/{id}/feedbacks/export")
    void exportCourseFeedbacks(
            @PathVariable("id") Integer id,
            @RequestParam("type") DocumentType documentType,
            HttpServletResponse response);

    @GetMapping("/users/{id}/lessons/export")
    void exportStudentLessons(
            @PathVariable("id") Integer id,
            @RequestParam("type") DocumentType documentType,
            HttpServletResponse response
    );
}
