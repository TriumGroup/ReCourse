package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.TeacherFeedback;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherFeedbackRepository extends PagingAndSortingRepository<TeacherFeedback, Long> {

    List<TeacherFeedback> findByTeacherIdOrderByIdDesc(long id);

    List<TeacherFeedback> findByTeacherIdOrderByIdDesc(long id, Pageable pageable);

    List<TeacherFeedback> findByStudentIdOrderByIdDesc(long id);

    List<TeacherFeedback> findByStudentIdOrderByIdDesc(long id, Pageable pageable);

}
