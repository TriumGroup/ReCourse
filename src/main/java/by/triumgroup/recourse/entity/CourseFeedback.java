package by.triumgroup.recourse.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "course_feedback")
public class CourseFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT(11)", nullable = false)
    private long id;

    @Column(columnDefinition = "INT(11)", nullable = false)
    private long courseId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "student_id")
    private User student;

    @Column(length = 50)
    private String header;

    @Column(columnDefinition = "TEXT")
    private String report;

    @Column(columnDefinition = "TEXT")
    private String pros;

    @Column(columnDefinition = "TEXT")
    private String cons;

    public CourseFeedback() {
    }

    public CourseFeedback(long id, long courseId, User student, String header, String report, String pros, String cons) {
        this.id = id;
        this.courseId = courseId;
        this.student = student;
        this.header = header;
        this.report = report;
        this.pros = pros;
        this.cons = cons;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
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

    public String getPros() {
        return pros;
    }

    public void setPros(String pros) {
        this.pros = pros;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseFeedback that = (CourseFeedback) o;
        return id == that.id &&
                courseId == that.courseId &&
                Objects.equals(student, that.student) &&
                Objects.equals(header, that.header) &&
                Objects.equals(report, that.report) &&
                Objects.equals(pros, that.pros) &&
                Objects.equals(cons, that.cons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, student, header, report, pros, cons);
    }

    @Override
    public String toString() {
        return "CourseFeedback{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", student=" + student +
                ", header='" + header + '\'' +
                ", report='" + report + '\'' +
                ", pros='" + pros + '\'' +
                ", cons='" + cons + '\'' +
                '}';
    }
}
