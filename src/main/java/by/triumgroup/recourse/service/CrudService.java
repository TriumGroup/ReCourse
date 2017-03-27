package by.triumgroup.recourse.service;

import by.triumgroup.recourse.service.exception.ServiceException;

public interface CrudService<E, ID> {

    E findById(ID id) throws ServiceException;

}
