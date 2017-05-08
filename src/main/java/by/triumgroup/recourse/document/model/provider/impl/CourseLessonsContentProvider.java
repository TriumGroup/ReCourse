package by.triumgroup.recourse.document.model.provider.impl;

import by.triumgroup.recourse.document.model.provider.ContentProvider;
import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.Lesson;

import java.util.*;
import java.util.stream.Collectors;

public class CourseLessonsContentProvider implements ContentProvider<Course, Lesson> {
    @Override
    public String createFilename(Course course) {
        return String.format("%s_lessons", course.getTitle());
    }

    @Override
    public String createTitle(Course course) {
        return course.getTitle();
    }

    @Override
    public Map<String, String> createSubtitles(Course course) {
        return new HashMap<String, String>(){{
            put("Description", course.getDescription());
        }};
    }

    @Override
    public List<String> getHeaders() {
        return Arrays.asList("Topic", "Start time", "Duration", "Teacher");
    }

    @Override
    public String createTableTitle(Course course, Collection<Lesson> lessons) {
        return "Lessons";
    }

    @Override
    public List<List<String>> createRows(Collection<Lesson> lessons) {
        return lessons.stream()
                .map(lesson -> Arrays.asList(lesson.getTopic(),
                        lesson.getStartTime().toString(),
                        lesson.getDuration().toString(),
                        lesson.getTeacher().getFullName()))
                .collect(Collectors.toList());
    }
}
