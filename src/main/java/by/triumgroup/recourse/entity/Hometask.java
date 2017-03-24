package by.triumgroup.recourse.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hometask")
public class Hometask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "INT(11)", nullable = false)
    private long id;

    @Column(columnDefinition = "INT(11)", nullable = false)
    private long lessonId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String task;

    public Hometask() {
    }

    public Hometask(long id, long lessonId, String task) {
        this.id = id;
        this.lessonId = lessonId;
        this.task = task;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLessonId() {
        return lessonId;
    }

    public void setLessonId(long lessonId) {
        this.lessonId = lessonId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hometask hometask = (Hometask) o;
        return id == hometask.id &&
                lessonId == hometask.lessonId &&
                Objects.equals(task, hometask.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lessonId, task);
    }

    @Override
    public String toString() {
        return "Hometask{" +
                "id=" + id +
                ", lessonId=" + lessonId +
                ", task='" + task + '\'' +
                '}';
    }
}
