package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.Lesson;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LessonRepository extends PagingAndSortingRepository<Lesson, Long> {

    List<Lesson> findByCourseIdOrderByStartTimeDesc(long id);

    List<Lesson> findByCourseIdOrderByStartTimeDesc(long id, Pageable pageable);

    List<Lesson> findByTeacherIdOrderByStartTimeDesc(long id);

    List<Lesson> findByTeacherIdOrderByStartTimeDesc(long id, Pageable pageable);

}
