package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.LessonRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.CrudServiceTest;
import by.triumgroup.recourse.service.LessonService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.LessonSupplier;
import org.mockito.Mockito;
import org.springframework.data.repository.CrudRepository;

public class LessonServiceTest extends CrudServiceTest<Lesson, Integer> {
    private LessonRepository lessonRepository;
    private LessonService lessonService;
    private LessonSupplier lessonSupplier;
    private CourseRepository courseRepository;
    private UserRepository userRepository;

    public LessonServiceTest() {
        lessonRepository = Mockito.mock(LessonRepository.class);
        courseRepository = Mockito.mock(CourseRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        lessonService = new LessonServiceImpl(lessonRepository, courseRepository, userRepository);
        lessonSupplier = new LessonSupplier();
    }

    @Override
    protected CrudService<Lesson, Integer> getCrudService() {
        return lessonService;
    }

    @Override
    protected CrudRepository<Lesson, Integer> getCrudRepository() {
        return lessonRepository;
    }

    @Override
    protected EntitySupplier<Lesson, Integer> getEntitySupplier() {
        return lessonSupplier;
    }
}
