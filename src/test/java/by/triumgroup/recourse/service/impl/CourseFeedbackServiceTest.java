package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.CourseFeedback;
import by.triumgroup.recourse.repository.CourseFeedbackRepository;
import by.triumgroup.recourse.service.CourseFeedbackService;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.CrudServiceTest;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.CourseFeedbackSupplier;
import org.mockito.Mockito;
import org.springframework.data.repository.CrudRepository;

public class CourseFeedbackServiceTest extends CrudServiceTest<CourseFeedback, Integer> {
    private CourseFeedbackService courseFeedbackService;
    private CourseFeedbackRepository courseFeedbackRepository;
    private CourseFeedbackSupplier courseFeedbackSupplier;

    public CourseFeedbackServiceTest() {
        courseFeedbackRepository = Mockito.mock(CourseFeedbackRepository.class);
        courseFeedbackService = new CourseFeedbackServiceImpl(courseFeedbackRepository);
        courseFeedbackSupplier = new CourseFeedbackSupplier();
    }

    @Override
    protected CrudService<CourseFeedback, Integer> getCrudService() {
        return courseFeedbackService;
    }

    @Override
    protected CrudRepository<CourseFeedback, Integer> getCrudRepository() {
        return courseFeedbackRepository;
    }

    @Override
    protected EntitySupplier<CourseFeedback, Integer> getEntitySupplier() {
        return courseFeedbackSupplier;
    }
}
