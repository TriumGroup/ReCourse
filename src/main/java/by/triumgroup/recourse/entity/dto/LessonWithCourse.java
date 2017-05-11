package by.triumgroup.recourse.entity.dto;

import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.Lesson;

public class LessonWithCourse extends Lesson {
    private Course course;

    public LessonWithCourse(Lesson lesson, Course course) {
        super(lesson);
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
