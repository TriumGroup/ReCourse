package by.triumgroup.recourse.entity.dto;

import by.triumgroup.recourse.entity.model.Course;

public class CourseWithMark extends Course {
    private Double averageMark;

    public CourseWithMark(String title, Double averageMark) {
        this.averageMark = averageMark;
        setTitle(title);
    }

    public void setAverageMark(Double averageMark) {
        this.averageMark = averageMark;
    }

    public Double getAverageMark() {
        return averageMark;
    }
}
