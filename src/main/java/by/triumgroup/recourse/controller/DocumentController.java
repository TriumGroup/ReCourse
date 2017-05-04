package by.triumgroup.recourse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public interface DocumentController {

    @GetMapping("/users/{id}/profile")
    void generateStudentProfile(
            @PathVariable("id") Integer id,
            HttpServletResponse response);

}
