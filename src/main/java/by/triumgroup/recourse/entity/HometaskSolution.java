package by.triumgroup.recourse.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hometask_solution")
public class HometaskSolution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT(11)", nullable = false)
    private long id;

    @Column(columnDefinition = "INT(11)", nullable = false)
    private long hometaskId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "student_id")
    private User student;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String solution;

    public HometaskSolution() {
    }

    public HometaskSolution(long id, long hometaskId, User student, String solution) {
        this.id = id;
        this.hometaskId = hometaskId;
        this.student = student;
        this.solution = solution;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getHometaskId() {
        return hometaskId;
    }

    public void setHometaskId(long hometaskId) {
        this.hometaskId = hometaskId;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HometaskSolution that = (HometaskSolution) o;
        return id == that.id &&
                hometaskId == that.hometaskId &&
                Objects.equals(student, that.student) &&
                Objects.equals(solution, that.solution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hometaskId, student, solution);
    }

    @Override
    public String toString() {
        return "HometaskSolution{" +
                "id=" + id +
                ", hometaskId=" + hometaskId +
                ", student=" + student +
                ", solution='" + solution + '\'' +
                '}';
    }
}
