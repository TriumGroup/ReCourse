package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.HometaskSolution;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HometaskSolutionService extends CrudService<HometaskSolution, Integer> {

    List<HometaskSolution> findByHometaskId(Integer id, Pageable pageable);

    List<HometaskSolution> findByStudentId(Integer id, Pageable pageable);

    HometaskSolution findByStudentIdAndLessonId(Integer studentId, Integer lessonId);

}
