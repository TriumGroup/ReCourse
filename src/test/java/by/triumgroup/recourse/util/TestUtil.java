package by.triumgroup.recourse.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.io.IOException;
import java.util.Arrays;

public class TestUtil {
    public static String toJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SafeVarargs
    public static <T> Page<T> newPage(T... content) {
        return new PageImpl<T>(Arrays.asList(content));
    }

    public static Errors createValidationErrors(Object object, String objectName) {
        return new BeanPropertyBindingResult(object, objectName);
    }
}
