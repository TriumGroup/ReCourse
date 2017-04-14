package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.model.TeacherFeedback;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherFeedbackRepository extends PagingAndSortingRepository<TeacherFeedback, Integer> {

    List<TeacherFeedback> findByTeacherIdOrderByIdDesc(Integer id, Pageable pageable);

    List<TeacherFeedback> findByStudentIdOrderByIdDesc(Integer id, Pageable pageable);

}
