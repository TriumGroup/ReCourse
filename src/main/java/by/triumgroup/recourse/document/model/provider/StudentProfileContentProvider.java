package by.triumgroup.recourse.document.model.provider;

import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.User;

import java.util.*;
import java.util.stream.Collectors;

public class StudentProfileContentProvider implements ContentProvider<User, Course> {
    @Override
    public String createTitle(User mainModel) {
        return mainModel.getName() + " " + mainModel.getSurname();
    }

    @Override
    public Map<String, String> createSubtitles(User mainModel) {
        return new HashMap<String, String>(){{
            put("Role", mainModel.getRole().name());
            put("Birthday", mainModel.getBirthday().toString());
        }};
    }

    @Override
    public List<String> getHeaders() {
        return Arrays.asList("Title", "Max students");
    }

    @Override
    public String createTableTitle(User user, Collection<Course> courses) {
        return "Courses";
    }

    @Override
    public List<List<String>> createRows(Collection<Course> courses) {
        return courses.stream()
                .map(course -> Arrays.asList(course.getTitle(), course.getMaxStudents().toString()))
                .collect(Collectors.toList());
    }
}
