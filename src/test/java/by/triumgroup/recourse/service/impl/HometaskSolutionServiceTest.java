package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.repository.HometaskRepository;
import by.triumgroup.recourse.repository.HometaskSolutionRepository;
import by.triumgroup.recourse.repository.UserRepository;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.CrudServiceTest;
import by.triumgroup.recourse.service.HometaskSolutionService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.HometaskSolutionSupplier;
import org.mockito.Mockito;
import org.springframework.data.repository.CrudRepository;

public class HometaskSolutionServiceTest extends CrudServiceTest<HometaskSolution, Integer> {
    private HometaskSolutionService hometaskSolutionService;
    private HometaskSolutionRepository hometaskSolutionRepository;
    private HometaskSolutionSupplier hometaskSolutionSupplier;
    private HometaskRepository hometaskRepository;
    private UserRepository userRepository;

    public HometaskSolutionServiceTest() {
        hometaskSolutionRepository = Mockito.mock(HometaskSolutionRepository.class);
        hometaskRepository = Mockito.mock(HometaskRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        hometaskSolutionService = new HometaskSolutionServiceImpl(hometaskSolutionRepository, hometaskRepository, userRepository);
        hometaskSolutionSupplier = new HometaskSolutionSupplier();
    }

    @Override
    protected CrudService<HometaskSolution, Integer> getCrudService() {
        return hometaskSolutionService;
    }

    @Override
    protected CrudRepository<HometaskSolution, Integer> getCrudRepository() {
        return hometaskSolutionRepository;
    }

    @Override
    protected EntitySupplier<HometaskSolution, Integer> getEntitySupplier() {
        return hometaskSolutionSupplier;
    }
}
