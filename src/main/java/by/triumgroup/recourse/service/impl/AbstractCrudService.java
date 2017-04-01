package by.triumgroup.recourse.service.impl;

import by.triumgroup.recourse.entity.BaseEntity;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.exception.ServiceException;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

import static by.triumgroup.recourse.service.exception.wrapper.ServiceExceptionWrapper.tryCallJPA;

public abstract class AbstractCrudService<E extends BaseEntity<ID>, ID extends Serializable> implements CrudService<E, ID> {

    private final CrudRepository<E, ID> repository;

    protected AbstractCrudService(CrudRepository<E, ID> repository) {
        this.repository = repository;
    }

    @Override
    public E findById(ID id) throws ServiceException {
        return tryCallJPA(() -> repository.findOne(id));
    }

    @Override
    public <S extends E> S save(S entity) throws ServiceException {
        return tryCallJPA(() -> repository.save(entity));
    }

    @Override
    public void delete(ID id) throws ServiceException {
        tryCallJPA(() -> repository.delete(id));
    }

    @Override
    public <S extends E> S update(S entity, ID id) throws ServiceException {
        entity.setId(id);
        return tryCallJPA(() -> repository.save(entity));
    }
}
