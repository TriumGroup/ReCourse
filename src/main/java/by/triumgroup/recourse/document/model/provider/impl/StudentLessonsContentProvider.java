package by.triumgroup.recourse.document.model.provider.impl;

import by.triumgroup.recourse.document.model.provider.ContentProvider;
import by.triumgroup.recourse.entity.dto.LessonWithCourse;
import by.triumgroup.recourse.entity.model.User;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static by.triumgroup.recourse.util.Util.lessonDurationToString;
import static by.triumgroup.recourse.util.Util.lessonStartTimeToString;

public class StudentLessonsContentProvider implements ContentProvider<User, LessonWithCourse> {
    @Override
    public String createFilename(User user) {
        return String.format("%s_lessons", user.getFullName());
    }

    @Override
    public String createTitle(User user) {
        return user.getFullName();
    }

    @Override
    public List<Pair<String, String>> createSubtitles(User user) {
        return Collections.emptyList();
    }

    @Override
    public List<String> getHeaders() {
        return Arrays.asList(
                "Course",
                "Topic",
                "Start time",
                "Duration",
                "Hometask",
                "Teacher"
        );
    }

    @Override
    public String createTableTitle(User user, Collection<LessonWithCourse> lessons) {
        return "Lessons";
    }

    @Override
    public List<List<String>> createRows(Collection<LessonWithCourse> lessons) {
        return lessons.stream()
                .map(lesson -> Arrays.asList(
                        lesson.getCourse().getTitle(),
                        lesson.getTopic(),
                        lessonStartTimeToString(lesson.getStartTime()),
                        lessonDurationToString(lesson.getDuration()),
                        hometaskToString(lesson.getTask()),
                        lesson.getTeacher().getFullName()))
                .collect(Collectors.toList());
    }

    private String hometaskToString(String hometask) {
        if (hometask != null && !hometask.isEmpty()) {
            return hometask;
        } else {
            return "No hometask";
        }
    }
}
