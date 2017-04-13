package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.StudentReport;
import by.triumgroup.recourse.repository.CourseRepository;
import by.triumgroup.recourse.repository.StudentReportRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.CrudServiceTest;
import by.triumgroup.recourse.service.StudentReportService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.StudentReportSupplier;
import org.mockito.Mockito;
import org.springframework.data.repository.CrudRepository;

public class StudentReportServiceTest extends CrudServiceTest<StudentReport, Integer> {
    private CourseRepository courseRepository;
    private UserRepository userRepository;
    private StudentReportService studentReportService;
    private StudentReportRepository studentReportRepository;
    private StudentReportSupplier studentReportSupplier;

    public StudentReportServiceTest() {
        courseRepository = Mockito.mock(CourseRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        studentReportRepository = Mockito.mock(StudentReportRepository.class);
        studentReportService = new StudentReportServiceImpl(studentReportRepository, courseRepository, userRepository);
        studentReportSupplier = new StudentReportSupplier();
    }

    @Override
    protected CrudService<StudentReport, Integer> getCrudService() {
        return studentReportService;
    }

    @Override
    protected CrudRepository<StudentReport, Integer> getCrudRepository() {
        return studentReportRepository;
    }

    @Override
    protected EntitySupplier<StudentReport, Integer> getEntitySupplier() {
        return studentReportSupplier;
    }
}
