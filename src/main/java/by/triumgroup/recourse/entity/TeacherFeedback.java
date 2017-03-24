package by.triumgroup.recourse.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teacher_feedback")
public class TeacherFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT(11)", nullable = false)
    private long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "student_id")
    private User student;

    @Column(length = 50)
    private String header;

    @Column(columnDefinition = "TEXT")
    private String report;

    public TeacherFeedback() {
    }

    public TeacherFeedback(User teacher, User student, String header, String report) {
        this.teacher = teacher;
        this.student = student;
        this.header = header;
        this.report = report;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
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
        TeacherFeedback that = (TeacherFeedback) o;
        return id == that.id &&
                Objects.equals(teacher, that.teacher) &&
                Objects.equals(student, that.student) &&
                Objects.equals(header, that.header) &&
                Objects.equals(report, that.report);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teacher, student, header, report);
    }

    @Override
    public String toString() {
        return "TeacherFeedback{" +
                "id=" + id +
                ", teacher=" + teacher +
                ", student=" + student +
                ", header='" + header + '\'' +
                ", report='" + report + '\'' +
                '}';
    }
}
