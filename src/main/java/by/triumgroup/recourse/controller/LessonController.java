package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.entity.Hometask;
import by.triumgroup.recourse.entity.Lesson;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lesson")
public interface LessonController extends CrudController<Lesson, Integer> {

    @GetMapping("{lessonId}/hometasks")
    List<Hometask> getHometasks(
            @PathVariable("lessonId") Integer lessonId, Pageable pageable);

}
