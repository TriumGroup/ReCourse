package by.triumgroup.recourse.util;

import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.util.Optional;

public class WrapperFunctions {

    @FunctionalInterface
    public interface WrapperFunction<R> {
        R call();
    }

    @FunctionalInterface
    public interface WrapperVoidFunction {
        void call();
    }

    @FunctionalInterface
    public interface WrapperOptionalFunction<R> {
        Optional<R> call();
    }

    @FunctionalInterface
    public interface DocumentGeneratorCall {
        void call() throws DocumentException, IOException;
    }
}
