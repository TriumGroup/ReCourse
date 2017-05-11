package by.triumgroup.recourse.document.model.provider.impl;

import by.triumgroup.recourse.document.model.provider.ContentProvider;
import by.triumgroup.recourse.entity.dto.CourseWithStudents;
import by.triumgroup.recourse.entity.dto.StudentWithMark;
import org.springframework.data.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

import static by.triumgroup.recourse.util.Util.optionalMarkToString;

public class CourseStudentsContentProvider implements ContentProvider<CourseWithStudents, StudentWithMark> {
    @Override
    public String createFilename(CourseWithStudents courseWithStudents) {
        return String.format("%s_students", courseWithStudents.getTitle());
    }

    @Override
    public String createTitle(CourseWithStudents courseWithStudents) {
        return courseWithStudents.getTitle();
    }

    @Override
    public List<Pair<String, String>> createSubtitles(CourseWithStudents courseWithStudents) {
        return Arrays.asList(
           Pair.of("Max students", courseWithStudents.getMaxStudents().toString()),
           Pair.of("Now registered", String.valueOf(courseWithStudents.getStudentsWithMarks().size()))
        );
    }

    @Override
    public List<String> getHeaders() {
        return Arrays.asList("Name", "Average mark");
    }

    @Override
    public String createTableTitle(CourseWithStudents courseWithStudents, Collection<StudentWithMark> studentWithMarks) {
        return "Students";
    }

    @Override
    public List<List<String>> createRows(Collection<StudentWithMark> studentWithMarks) {
        return studentWithMarks.stream()
                .map(studentWithMark -> Arrays.asList(studentWithMark.getFullName(), optionalMarkToString(studentWithMark.getMark())))
                .collect(Collectors.toList());
    }
}
