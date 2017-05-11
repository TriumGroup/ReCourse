package by.triumgroup.recourse.document.model.provider.impl;

import by.triumgroup.recourse.document.model.provider.ContentProvider;
import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.CourseFeedback;
import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CourseFeedbacksContentProvider implements ContentProvider<Course, CourseFeedback> {
    @Override
    public String createFilename(Course course) {
        return String.format("%s_feedbacks", course.getTitle());
    }

    @Override
    public String createTitle(Course course) {
        return course.getTitle();
    }

    @Override
    public List<Pair<String, String>> createSubtitles(Course course) {
        return Collections.singletonList(
                Pair.of("Description", course.getDescription())
        );
    }

    @Override
    public List<String> getHeaders() {
        return Arrays.asList("Student", "Heading", "Feedback");
    }

    @Override
    public String createTableTitle(Course course, Collection<CourseFeedback> courseFeedbacks) {
        return "Feedbacks";
    }

    @Override
    public List<List<String>> createRows(Collection<CourseFeedback> courseFeedbacks) {
        return courseFeedbacks.stream()
                .map(courseFeedback -> Arrays.asList(
                        courseFeedback.getStudent().getFullName(),
                        courseFeedback.getHeading(),
                        formatFeedbackBody(courseFeedback)))
                .collect(Collectors.toList());
    }

    private String formatFeedbackBody(CourseFeedback courseFeedback) {
        return String.format("%s\nPros: %s\nCons: %s",
                courseFeedback.getReport(),
                courseFeedback.getPros(),
                courseFeedback.getCons());
    }
}
