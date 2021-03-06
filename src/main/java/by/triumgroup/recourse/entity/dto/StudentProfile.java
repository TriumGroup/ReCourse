package by.triumgroup.recourse.entity.dto;

import by.triumgroup.recourse.entity.model.User;

import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;

public class StudentProfile extends User {
    private Double totalAverageMark;
    private List<CourseWithMark> averageMarks;

    public StudentProfile(User user, List<CourseWithMark> averageMarks) {
        super(user);
        OptionalDouble totalAverageMarkOptional = averageMarks.stream()
                .map(CourseWithMark::getAverageMark)
                .filter(Objects::nonNull)
                .mapToDouble(x -> x)
                .average();
        if (totalAverageMarkOptional.isPresent()){
            this.totalAverageMark = totalAverageMarkOptional.getAsDouble();
        } else {
            this.totalAverageMark = null;
        }
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
