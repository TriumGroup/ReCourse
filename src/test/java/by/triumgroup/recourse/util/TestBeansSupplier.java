package by.triumgroup.recourse.util;

import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.entity.BaseEntity;
import by.triumgroup.recourse.service.CrudService;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;

public class TestBeansSupplier<E extends BaseEntity<ID>, ID> {
    private CrudController<E, ID> crudController;
    private CrudService<E, ID> crudService;


    public TestBeansSupplier(Class<? extends CrudController<E, ID>> controllerClass, Class<? extends CrudService<E, ID>> serviceClass) {
        crudService = Mockito.mock(serviceClass);
        try {
            crudController = controllerClass.getDeclaredConstructor(serviceClass).newInstance(crudService);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public CrudController<E, ID> getController() {
        return crudController;
    }

    public CrudService<E, ID> getService() {
        return crudService;
    }
}