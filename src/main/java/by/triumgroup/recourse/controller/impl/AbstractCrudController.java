package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.exception.ControllerException;
import by.triumgroup.recourse.controller.exception.EntityNotFoundException;
import by.triumgroup.recourse.entity.BaseEntity;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.exception.ServiceException;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public abstract class AbstractCrudController<E extends BaseEntity<ID>, ID> implements CrudController<E, ID> {

    private final Logger logger;
    private final CrudService<E, ID> crudService;

    AbstractCrudController(CrudService<E, ID> crudService, Logger logger) {
        this.logger = logger;
        this.crudService = crudService;
    }

    public E getById(@PathVariable("id") ID id) throws ControllerException {
        E entity;
        try {
            entity = crudService.findById(id);
            if (entity == null) {
                logger.trace("Entity with id " + id + " not found");
                throw new EntityNotFoundException();
            }
        } catch (ServiceException e) {
            logger.warn("Cannot find entity with id " + id + "\n", e);
            throw new ControllerException(e);
        }
        logger.trace("User with id " + id + " has been found");
        return entity;
    }

    @Override
    public <S extends E> S create(@Valid @RequestBody S entity) throws ControllerException {
        try {
            return crudService.save(entity);
        } catch (ServiceException e) {
            logger.warn("Cannot create entity with \n" + entity + "\n", e);
            throw new ControllerException(e);
        }
    }

    @Override
    public <S extends E> S update(@Valid @RequestBody S entity, @PathVariable("id") ID id) throws ControllerException {
        S updatedEntity;
        try {
            updatedEntity = crudService.update(entity, id);
            if (updatedEntity == null) {
                logger.trace("Entity with id " + id + " not found");
                throw new EntityNotFoundException();
            }
        } catch (ServiceException e) {
            logger.warn("Cannot update entity with \n" + entity + "\n", e);
            throw new ControllerException(e);
        }
        return updatedEntity;
    }

    @Override
    public void delete(@PathVariable("id") ID id) throws ControllerException {
        try {
            crudService.delete(id);
            logger.trace("Entity with id " + id + " has been deleted.");
        } catch (ServiceException e) {
            logger.warn("Cannot delete entity with id " + id + "\n", e);
            throw new ControllerException(e);
        }
    }

}
