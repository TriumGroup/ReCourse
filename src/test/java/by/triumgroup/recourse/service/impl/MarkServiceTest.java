package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.Mark;
import by.triumgroup.recourse.repository.MarkRepository;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.CrudServiceTest;
import by.triumgroup.recourse.service.MarkService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.MarkSupplier;
import org.mockito.Mockito;
import org.springframework.data.repository.CrudRepository;

public class MarkServiceTest extends CrudServiceTest<Mark, Integer> {
    private MarkService markService;
    private MarkRepository markRepository;
    private MarkSupplier markSupplier;

    public MarkServiceTest() {
        markRepository = Mockito.mock(MarkRepository.class);
        markService = new MarkServiceImpl(markRepository);
        markSupplier = new MarkSupplier();
    }

    @Override
    protected CrudService<Mark, Integer> getCrudService() {
        return markService;
    }

    @Override
    protected CrudRepository<Mark, Integer> getCrudRepository() {
        return markRepository;
    }

    @Override
    protected EntitySupplier<Mark, Integer> getEntitySupplier() {
        return markSupplier;
    }
}
