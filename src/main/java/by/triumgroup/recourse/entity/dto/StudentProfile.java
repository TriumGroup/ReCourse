package by.triumgroup.recourse.entity.dto;

import by.triumgroup.recourse.entity.model.User;

import java.util.List;
import java.util.Objects;

public class StudentProfile extends User {
    private Double totalAverageMark;
    private List<CourseWithMark> averageMarks;

    public StudentProfile(User user, List<CourseWithMark> averageMarks) {
        super(user);
        this.totalAverageMark = averageMarks.stream()
                .map(CourseWithMark::getAverageMark)
                .filter(Objects::nonNull)
                .mapToDouble(x -> x)
                .average().getAsDouble();
        this.averageMarks = averageMarks;
    }

    public Double getTotalAverageMark() {
        return totalAverageMark;
    }

    public void setTotalAverageMark(Double totalAverageMark) {
        this.totalAverageMark = totalAverageMark;
    }

    public List<CourseWithMark> getAverageMarks() {
        return averageMarks;
    }

    public void setAverageMarks(List<CourseWithMark> averageMarks) {
        this.averageMarks = averageMarks;
    }
}
