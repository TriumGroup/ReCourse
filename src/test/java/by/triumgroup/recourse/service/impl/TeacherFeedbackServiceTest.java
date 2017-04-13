package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.TeacherFeedback;
import by.triumgroup.recourse.repository.TeacherFeedbackRepository;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.CrudServiceTest;
import by.triumgroup.recourse.service.TeacherFeedbackService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.TeacherFeedbackSupplier;
import org.mockito.Mockito;
import org.springframework.data.repository.CrudRepository;

public class TeacherFeedbackServiceTest extends CrudServiceTest<TeacherFeedback, Integer> {
    private TeacherFeedbackService teacherFeedbackService;
    private TeacherFeedbackRepository teacherFeedbackRepository;
    private TeacherFeedbackSupplier teacherFeedbackSupplier;

    public TeacherFeedbackServiceTest() {
        teacherFeedbackRepository = Mockito.mock(TeacherFeedbackRepository.class);
        teacherFeedbackService = new TeacherFeedbackServiceImpl(teacherFeedbackRepository);
        teacherFeedbackSupplier = new TeacherFeedbackSupplier();
    }

    @Override
    protected CrudService<TeacherFeedback, Integer> getCrudService() {
        return teacherFeedbackService;
    }

    @Override
    protected CrudRepository<TeacherFeedback, Integer> getCrudRepository() {
        return teacherFeedbackRepository;
    }

    @Override
    protected EntitySupplier<TeacherFeedback, Integer> getEntitySupplier() {
        return teacherFeedbackSupplier;
    }
}
