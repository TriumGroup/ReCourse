package by.triumgroup.recourse.repository;

import by.triumgroup.recourse.entity.dto.StudentCourseAverageMark;
import by.triumgroup.recourse.entity.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    User findByEmail(String email);

    List<User> findByRole(User.Role role, Pageable pageable);

    @Query(value = "SELECT AVG(mark.score) \n" +
            "  FROM mark \n" +
            "  JOIN hometask_solution \n" +
            "      ON mark.solution_id = hometask_solution.id\n" +
            "  JOIN lesson\n" +
            "      ON hometask_solution.lesson_id = lesson.id\n" +
            "  JOIN course\n" +
            "    ON lesson.course_id = course.id\n" +
            "  JOIN course_student\n" +
            "    ON course.id = course_student.course_id\n" +
            "  WHERE (course_student.student_id = :studentId)", nativeQuery = true)
    Double getStudentAverageMark(@Param("studentId")Integer studentId);

    List<StudentCourseAverageMark> getStudentAverageMarksByCourses(@Param("studentId") Integer studentId);
}
