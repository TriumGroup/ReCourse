package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.CrudControllerTest;
import by.triumgroup.recourse.controller.HometaskController;
import by.triumgroup.recourse.entity.model.Hometask;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.HometaskService;
import by.triumgroup.recourse.service.HometaskSolutionService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.HometaskSupplier;
import org.mockito.Mockito;

public class HometaskControllerTest extends CrudControllerTest<Hometask, Integer> {
    private HometaskController hometaskController;
    private HometaskService hometaskService;
    private HometaskSupplier hometaskSupplier;
    private HometaskSolutionService hometaskSolutionService;

    public HometaskControllerTest() {
        hometaskService = Mockito.mock(HometaskService.class);
        hometaskSolutionService = Mockito.mock(HometaskSolutionService.class);
        hometaskController = new HometaskControllerImpl(hometaskService, hometaskSolutionService);
        hometaskSupplier = new HometaskSupplier();
    }

    @Override
    protected CrudController<Hometask, Integer> getController() {
        return hometaskController;
    }

    @Override
    protected CrudService<Hometask, Integer> getService() {
        return hometaskService;
    }

    @Override
    protected String getEntityName() {
        return "hometask";
    }

    @Override
    protected EntitySupplier<Hometask, Integer> getEntitySupplier() {
        return hometaskSupplier;
    }
}
