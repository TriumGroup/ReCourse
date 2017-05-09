package by.triumgroup.recourse.util;

import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Util {

    private static final PageRequest ALL_ITERMS_PAGE_REQUEST = new PageRequest(0, Integer.MAX_VALUE);

    private static final SimpleDateFormat LESSON_START_TIME_FORMAT = new SimpleDateFormat("EEEE, d MMMM HH:mm", Locale.US);

    private static final SimpleDateFormat LESSON_DURATION_TIME_FORMAT = new SimpleDateFormat("HH:mm", Locale.US);

    public static boolean ifExistsWithRole(UserRepository repository, Integer id, User.Role role) {
        User user = repository.findOne(id);
        return (user != null) && (user.getRole() == role);
    }

    public static Pageable allItemsPage() {
        return ALL_ITERMS_PAGE_REQUEST;
    }

    public static <T> T ifNullDefault(T toTest, T defaultValue) {
        return toTest == null ? defaultValue : toTest;
    }

    public static String sanitizeFilename(String filename) {
        return filename.replaceAll("[^a-zA-Z0-9.-]", "_");
    }

    public static String optionalMarkToString(Double mark) {
        if (mark == null) {
            return "No marks";
        } else {
            return mark.toString();
        }
    }

    public static String lessonStartTimeToString(Timestamp startTime) {
        return LESSON_START_TIME_FORMAT.format(startTime);
    }

    public static String lessonDurationToString(Time duration) {
        return LESSON_DURATION_TIME_FORMAT.format(duration);
    }
}
