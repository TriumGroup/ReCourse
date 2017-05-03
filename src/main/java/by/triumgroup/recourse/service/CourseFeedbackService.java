package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.model.CourseFeedback;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CourseFeedbackService extends CrudService<CourseFeedback, Integer> {

    Optional<List<CourseFeedback>> findByCourseId(Integer id, Pageable pageable);

}
