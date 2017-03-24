package by.triumgroup.recourse.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mark")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT(11)", nullable = false)
    private long id;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private int score;

    @Column(columnDefinition = "INT(11)", nullable = false)
    private long solutionId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    public Mark() {
    }

    public Mark(long id, int score, long solutionId, String comment) {
        this.id = id;
        this.score = score;
        this.solutionId = solutionId;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(long solutionId) {
        this.solutionId = solutionId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark = (Mark) o;
        return id == mark.id &&
                score == mark.score &&
                solutionId == mark.solutionId &&
                Objects.equals(comment, mark.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, solutionId, comment);
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", score=" + score +
                ", solutionId=" + solutionId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
