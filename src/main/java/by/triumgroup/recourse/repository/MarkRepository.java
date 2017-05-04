package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.model.Mark;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface MarkRepository extends PagingAndSortingRepository<Mark, Integer> {

    Mark findBySolutionId(Integer id);

    @Query(value = "SELECT *\n" +
            "FROM mark\n" +
            "WHERE solution_id =\n" +
            "      (SELECT id\n" +
            "       FROM hometask_solution\n" +
            "       WHERE (student_id = :student_id) AND \n" +
            "             (lesson_id = :lesson_id))", nativeQuery = true)
    Mark findByLessonIdAndStudentId(@Param("lesson_id") Integer lessonId,
                                    @Param("student_id") Integer studentId);

}
