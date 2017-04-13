package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.CrudControllerTest;
import by.triumgroup.recourse.controller.HometaskSolutionController;
import by.triumgroup.recourse.entity.model.HometaskSolution;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.HometaskSolutionService;
import by.triumgroup.recourse.service.MarkService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.HometaskSolutionSupplier;
import org.mockito.Mockito;

public class HometaskSolutionControllerTest extends CrudControllerTest<HometaskSolution, Integer> {
    private HometaskSolutionController hometaskSolutionController;
    private HometaskSolutionService hometaskSolutionService;
    private HometaskSolutionSupplier hometaskSolutionSupplier;
    private MarkService markService;

    public HometaskSolutionControllerTest() {
        markService = Mockito.mock(MarkService.class);
        hometaskSolutionService = Mockito.mock(HometaskSolutionService.class);
        hometaskSolutionController = new HometaskSolutionControllerImpl(hometaskSolutionService, markService);
        hometaskSolutionSupplier = new HometaskSolutionSupplier();
    }

    @Override
    protected CrudController<HometaskSolution, Integer> getController() {
        return hometaskSolutionController;
    }

    @Override
    protected CrudService<HometaskSolution, Integer> getService() {
        return hometaskSolutionService;
    }

    @Override
    protected String getEntityName() {
        return "hometask/solution";
    }

    @Override
    protected EntitySupplier<HometaskSolution, Integer> getEntitySupplier() {
        return hometaskSolutionSupplier;
    }
}
