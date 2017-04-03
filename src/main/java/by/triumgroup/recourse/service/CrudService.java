package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.BaseEntity;
import by.triumgroup.recourse.service.exception.ServiceException;

public interface CrudService<E extends BaseEntity<ID>, ID> {

    E findById(ID id) throws ServiceException;

    <S extends E> S add(S entity) throws ServiceException;

    <S extends E> S update(S entity, ID id) throws ServiceException;

    void delete(ID id) throws ServiceException;

}
