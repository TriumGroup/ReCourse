package by.triumgroup.recourse.entity.dto;

import by.triumgroup.recourse.entity.model.User;

import java.util.List;

public class Student extends User {
    private Double totalAverageMark;
    private List<StudentCourseAverageMark> averageMarks;

    public Student(User user, Double totalAverageMark, List<StudentCourseAverageMark> averageMarks) {
        super(user);
        this.totalAverageMark = totalAverageMark;
        this.averageMarks = averageMarks;
    }

    public Double getTotalAverageMark() {
        return totalAverageMark;
    }

    public void setTotalAverageMark(Double totalAverageMark) {
        this.totalAverageMark = totalAverageMark;
    }

    public List<StudentCourseAverageMark> getAverageMarks() {
        return averageMarks;
    }

    public void setAverageMarks(List<StudentCourseAverageMark> averageMarks) {
        this.averageMarks = averageMarks;
    }
}
