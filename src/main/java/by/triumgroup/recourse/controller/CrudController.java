package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.controller.exception.EntityNotFoundException;
import by.triumgroup.recourse.service.exception.ServiceException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface CrudController<E, ID> {

    @GetMapping("{id}")
    E getById(@PathVariable("id") ID id) throws EntityNotFoundException, ServiceException;

}
