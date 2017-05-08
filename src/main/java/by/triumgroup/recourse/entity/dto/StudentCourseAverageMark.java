package by.triumgroup.recourse.entity.dto;

public class StudentCourseAverageMark {
    private String title;
    private Double averageMark;

    public StudentCourseAverageMark(String title, Double averageMark) {
        this.title = title;
        this.averageMark = averageMark;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(Double averageMark) {
        this.averageMark = averageMark;
    }
}
