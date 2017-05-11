package by.triumgroup.recourse.document.model.provider.impl;

import by.triumgroup.recourse.document.model.provider.ContentProvider;
import by.triumgroup.recourse.entity.dto.StudentProfile;
import by.triumgroup.recourse.entity.dto.CourseWithMark;
import org.springframework.data.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

import static by.triumgroup.recourse.util.Util.optionalMarkToString;

public class StudentProfileContentProvider implements ContentProvider<StudentProfile, CourseWithMark> {
    @Override
    public String createTitle(StudentProfile mainEntity) {
        return mainEntity.getFullName();
    }

    @Override
    public String createFilename(StudentProfile user) {
        return String.format("%s_profile", user.getFullName());
    }

    @Override
    public List<Pair<String, String>> createSubtitles(StudentProfile mainEntity) {
        return Collections.singletonList(
                Pair.of("Total average mark", optionalMarkToString(mainEntity.getTotalAverageMark()))
        );
    }

    @Override
    public List<String> getHeaders() {
        return Arrays.asList("Title", "Average mark");
    }

    @Override
    public String createTableTitle(StudentProfile user, Collection<CourseWithMark> courses) {
        return "Courses";
    }

    @Override
    public List<List<String>> createRows(Collection<CourseWithMark> courses) {
        return courses.stream()
                .map(studentCourse -> Arrays.asList(studentCourse.getTitle(), optionalMarkToString(studentCourse.getAverageMark())))
                .collect(Collectors.toList());
    }
}
