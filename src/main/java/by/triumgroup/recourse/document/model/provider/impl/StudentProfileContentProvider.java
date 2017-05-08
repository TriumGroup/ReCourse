package by.triumgroup.recourse.document.model.provider.impl;

import by.triumgroup.recourse.document.model.provider.ContentProvider;
import by.triumgroup.recourse.entity.dto.Student;
import by.triumgroup.recourse.entity.dto.StudentCourseAverageMark;

import java.util.*;
import java.util.stream.Collectors;

public class StudentProfileContentProvider implements ContentProvider<Student, StudentCourseAverageMark> {
    @Override
    public String createTitle(Student mainEntity) {
        return mainEntity.getName() + " " + mainEntity.getSurname();
    }

    @Override
    public String createFilename(Student user) {
        return String.format("%s_%s_profile", user.getName(), user.getSurname());
    }

    @Override
    public Map<String, String> createSubtitles(Student mainEntity) {
        return new HashMap<String, String>(){{
            put("Total average mark", mainEntity.getTotalAverageMark().toString());
        }};
    }

    @Override
    public List<String> getHeaders() {
        return Arrays.asList("Title", "Average mark");
    }

    @Override
    public String createTableTitle(Student user, Collection<StudentCourseAverageMark> courses) {
        return "Courses";
    }

    @Override
    public List<List<String>> createRows(Collection<StudentCourseAverageMark> courses) {
        return courses.stream()
                .map(studentCourse -> Arrays.asList(studentCourse.getTitle(), studentCourse.getAverageMark().toString()))
                .collect(Collectors.toList());
    }
}
