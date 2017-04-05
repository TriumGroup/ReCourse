package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.CourseFeedback;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseFeedbackService extends CrudService<CourseFeedback, Integer> {

    List<CourseFeedback> findByCourseId(Integer id, Pageable pageable);

}
