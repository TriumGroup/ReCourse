package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.model.Lesson;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public interface LessonRepository extends PagingAndSortingRepository<Lesson, Integer> {

    List<Lesson> findByCourseIdOrderByStartTimeAsc(Integer id, Pageable pageable);

    List<Lesson> findByOrderByStartTimeAsc(Pageable pageable);

    @Query(value = "SELECT\n" +
            "  lesson.id,\n" +
            "  lesson.start_time,\n" +
            "  lesson.duration,\n" +
            "  lesson.course_id,\n" +
            "  lesson.topic,\n" +
            "  lesson.teacher_id,\n" +
            "  lesson.task\n" +
            "FROM lesson\n" +
            "  JOIN course ON lesson.course_id = course.id\n" +
            "WHERE (course.status != 'DRAFT')\n" +
            "ORDER BY start_time ASC \n#pageable\n",
            nativeQuery = true)
    List<Lesson> findAllExcludeDraftOrderByTimeAsc(Pageable pageable);

    List<Lesson> findByTeacherIdOrderByStartTimeAsc(Integer id, Pageable pageable);

    List<Lesson> findByTeacherIdAndCourseIdOrderByStartTimeAsc(Integer teacherId, Integer courseId, Pageable pageable);

    Lesson findFirstByCourseIdOrderByStartTimeAsc(Integer id);

    @Query(value = "SELECT recourse.can_add_lesson(:teacher_id, :new_lesson_start_time, :duration)", nativeQuery = true)
    boolean canAddLesson(@Param("new_lesson_start_time") Timestamp startTime,
                         @Param("teacher_id") Integer teacherId,
                         @Param("duration") Time duration);

    @Query(value = "SELECT recourse.can_update_lesson(:teacher_id, :new_lesson_start_time, :duration, :lesson_id)", nativeQuery = true)
    boolean canUpdateLesson(@Param("new_lesson_start_time") Timestamp startTime,
                            @Param("teacher_id") Integer teacherId,
                            @Param("duration") Time duration,
                            @Param("lesson_id") Integer lessonId);


}