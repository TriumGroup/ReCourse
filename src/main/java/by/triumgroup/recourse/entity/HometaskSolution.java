package by.triumgroup.recourse.entity;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "hometask_solution")
public class HometaskSolution extends BaseEntity<Integer>{
    @Column(columnDefinition = "INT(11)", nullable = false)
    private Long hometaskId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "student_id")
    private User student;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String solution;

    @OneToOne(mappedBy = "hometask_solution", cascade = {REFRESH, REMOVE, MERGE, DETACH})
    private Mark mark;

    public HometaskSolution() {
    }

    public HometaskSolution(Long hometaskId, User student, String solution, Mark mark) {
        this.hometaskId = hometaskId;
        this.student = student;
        this.solution = solution;
        this.mark = mark;
    }

    public Long getHometaskId() {
        return hometaskId;
    }

    public void setHometaskId(Long hometaskId) {
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

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }
}
