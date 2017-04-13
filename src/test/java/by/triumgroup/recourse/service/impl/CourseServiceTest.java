package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.service.CourseService;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.CrudServiceTest;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.CourseSupplier;
import org.mockito.Mockito;
import org.springframework.data.repository.CrudRepository;

public class CourseServiceTest extends CrudServiceTest<Course, Integer> {
    private CourseService courseService;
    private CourseRepository courseRepository;
    private CourseSupplier courseSupplier;

    public CourseServiceTest() {
        courseRepository = Mockito.mock(CourseRepository.class);
        courseService = new CourseServiceImpl(courseRepository);
        courseSupplier = new CourseSupplier();
    }

    @Override
    protected CrudService<Course, Integer> getCrudService() {
        return courseService;
    }

    @Override
    protected CrudRepository<Course, Integer> getCrudRepository() {
        return courseRepository;
    }

    @Override
    protected EntitySupplier<Course, Integer> getEntitySupplier() {
        return courseSupplier;
    }
}
