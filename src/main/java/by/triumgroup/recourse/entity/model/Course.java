package by.triumgroup.recourse.entity.model;

import by.triumgroup.recourse.entity.dto.StudentWithMark;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "course")
@SqlResultSetMapping(
        name = "StudentWithMarkMapping",
        classes = @ConstructorResult(
                targetClass = StudentWithMark.class,
                columns = {
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "surname", type = String.class),
                        @ColumnResult(name = "mark", type = Double.class)
                }
        )
)
@NamedNativeQuery(
        name = "Course.getStudentsAverageMarksForCourse",
        query = "SELECT user.name, user.surname, AVG(mark.score) as mark\n" +
                "FROM user\n" +
                "  JOIN course_student\n" +
                "    ON user.id = course_student.student_id\n" +
                "  LEFT JOIN lesson\n" +
                "    ON course_student.course_id = lesson.course_id\n" +
                "  LEFT JOIN hometask_solution\n" +
                "    ON course_student.student_id = hometask_solution.student_id AND hometask_solution.lesson_id = lesson.id\n" +
                "  LEFT JOIN mark\n" +
                "    ON mark.solution_id = hometask_solution.id\n" +
                "WHERE lesson.course_id = :courseId\n" +
                "GROUP BY user.id\n",
        resultSetMapping = "StudentWithMarkMapping"
)
public class Course extends BaseEntity<Integer> {

    @NotNull(message = "Title is not specified")
    @SafeHtml
    @Column(length = 50, nullable = false)
    private String title;

    @NotNull(message = "Description is not specified")
    @SafeHtml
    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @NotNull(message = "Status is not specified")
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM ('DRAFT', 'PUBLISHED', 'FINISHED')", nullable = false)
    private Status status;

    @NotNull(message = "Registration end is not specified")
    @Column(columnDefinition = "DATETIME", nullable = false)
    private Timestamp registrationEnd;


    @NotNull(message = "Max students count is not specified")
    @Range(min = 1, max = 100, message = "Max students count must be in range 1-100")
    private Integer maxStudents;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="course_student",
            joinColumns=@JoinColumn(name="course_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="student_id", referencedColumnName="id"))
    private Set<User> students;

    public Course() {
    }

    public Course(String title, String description, Status status, Timestamp registrationEnd, Integer maxStudents) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.registrationEnd = registrationEnd;
        this.maxStudents = maxStudents;
    }

    public Course(Course course) {
        this.title = course.title;
        this.description = course.description;
        this.status = course.status;
        this.registrationEnd = course.registrationEnd;
        this.maxStudents = course.maxStudents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(Integer maxStudents) {
        this.maxStudents = maxStudents;
    }

    public Set<User> getStudents() {
        if (students == null) {
            return Collections.emptySet();
        }
        return students;
    }

    public void setStudents(Set<User> students) {
        this.students = students;
    }

    public Timestamp getRegistrationEnd() {
        return registrationEnd;
    }

    public void setRegistrationEnd(Timestamp registrationEnd) {
        this.registrationEnd = registrationEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Course course = (Course) o;
        return Objects.equals(title, course.title) &&
                Objects.equals(description, course.description) &&
                status == course.status &&
                Objects.equals(registrationEnd, course.registrationEnd) &&
                Objects.equals(maxStudents, course.maxStudents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, description, status, registrationEnd, maxStudents);
    }

    public enum Status {
        DRAFT, PUBLISHED, FINISHED
    }
}
