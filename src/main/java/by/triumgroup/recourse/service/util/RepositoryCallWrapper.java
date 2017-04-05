package by.triumgroup.recourse.service.util;

import by.triumgroup.recourse.service.exception.ServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

public class RepositoryCallWrapper {

    public static <R> R tryCallJPA(RepositoryFunction<R> function) throws ServiceException {
        try {
            return function.call();
        } catch (DataAccessException e) {
            throw new ServiceException(e);
        }
    }

    public static void tryCallJPA(RepositoryVoidFunction function) throws ServiceException {
        try {
            function.call();
        } catch (DataAccessException e) {
            throw new ServiceException(e);
        }
    }

    public static <T> Optional<T> wrapToOptional(RepositoryFunction<T> function) {
        return tryCallJPA(() -> {
            Optional<T> result;
            try {
                result = Optional.ofNullable(function.call());
            } catch (EmptyResultDataAccessException e) {
                result = Optional.empty();
            }
            return result;
        });
    }

    public static Optional<Boolean> wrapToBoolean(RepositoryVoidFunction function) {
        return tryCallJPA(() -> {
            Optional<Boolean> result = Optional.of(true);
            try {
                function.call();
            } catch (EmptyResultDataAccessException e) {
                result = Optional.empty();
            }
            return result;
        });
    }

    @FunctionalInterface
    public interface RepositoryFunction<R> {
        R call() throws DataAccessException, ServiceException;
    }

    @FunctionalInterface
    public interface RepositoryVoidFunction {
        void call() throws DataAccessException, ServiceException;
    }


}
