package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.document.DocumentType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public interface DocumentController {

    @GetMapping("/users/{id}/profile")
    void generateStudentProfile(
            @PathVariable("id") Integer id,
            @RequestParam("type") DocumentType documentType,
            HttpServletResponse response);

}
