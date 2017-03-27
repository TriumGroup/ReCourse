package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.controller.exception.EntityNotFoundException;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.exception.ServiceException;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Objects;

public class AbstractCrudController<E, ID> implements CrudController<E, ID> {


    private final CrudService<E, ID> crudService;

    protected AbstractCrudController(CrudService<E, ID> crudService) {
        this.crudService = crudService;
    }

    public E getById(@PathVariable("id") ID id) throws EntityNotFoundException, ServiceException {
        E entity;
        try {
            entity = crudService.findById(id);
            if (Objects.isNull(entity)) {
                throw new EntityNotFoundException();
            }
        } catch (ServiceException e) {
            throw new EntityNotFoundException();
        }
        return entity;
    }

}
