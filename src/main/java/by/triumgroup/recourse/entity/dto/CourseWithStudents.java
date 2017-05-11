package by.triumgroup.recourse.entity.dto;

import by.triumgroup.recourse.entity.model.Course;

import java.util.List;

public class CourseWithStudents extends Course {
    private List<StudentWithMark> studentsWithMarks;

    public CourseWithStudents(Course course, List<StudentWithMark> studentsWithMarks) {
        super(course);
        this.studentsWithMarks = studentsWithMarks;
    }

    public List<StudentWithMark> getStudentsWithMarks() {
        return studentsWithMarks;
    }

    public void setStudentsWithMarks(List<StudentWithMark> studentsWithMarks) {
        this.studentsWithMarks = studentsWithMarks;
    }
}
