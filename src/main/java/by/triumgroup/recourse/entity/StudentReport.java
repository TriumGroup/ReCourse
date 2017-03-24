package by.triumgroup.recourse.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student_report")
public class StudentReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT(11)", nullable = false)
    private long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @Column(columnDefinition = "INT(11)", nullable = false)
    private long courseId;

    @Column(length = 50)
    private String header;

    @Column(columnDefinition = "TEXT")
    private String report;

    public StudentReport() {
    }

    public StudentReport(long id, User student, User teacher, long courseId, String header, String report) {
        this.id = id;
        this.student = student;
        this.teacher = teacher;
        this.courseId = courseId;
        this.header = header;
        this.report = report;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentReport that = (StudentReport) o;
        return id == that.id &&
                courseId == that.courseId &&
                Objects.equals(student, that.student) &&
                Objects.equals(teacher, that.teacher) &&
                Objects.equals(header, that.header) &&
                Objects.equals(report, that.report);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, teacher, courseId, header, report);
    }

    @Override
    public String toString() {
        return "StudentReport{" +
                "id=" + id +
                ", student=" + student +
                ", teacher=" + teacher +
                ", courseId=" + courseId +
                ", header='" + header + '\'' +
                ", report='" + report + '\'' +
                '}';
    }
}
