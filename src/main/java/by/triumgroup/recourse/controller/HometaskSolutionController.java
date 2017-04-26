package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.configuration.security.Auth;
import by.triumgroup.recourse.configuration.security.UserAuthDetails;
import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.entity.model.Mark;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hometasks/solutions")
public interface HometaskSolutionController extends CrudController<HometaskSolution, Integer> {

    @GetMapping("{solutionId}/mark")
    Mark getMark(@PathVariable("solutionId") Integer solutionId);

    @GetMapping("/student/{studentId}")
    List<HometaskSolution> getSolutions(
            @PathVariable("studentId") Integer studentId,
            @Auth UserAuthDetails authDetails,
            Pageable pageable
    );

    @GetMapping(value = "/student/{studentId}", params = "lessonId")
    HometaskSolution getSolutionForLesson(
            @PathVariable("studentId") Integer studentId,
            @RequestParam("lessonId") Integer lessonId,
            @Auth UserAuthDetails authDetails,
            Pageable pageable);

}
