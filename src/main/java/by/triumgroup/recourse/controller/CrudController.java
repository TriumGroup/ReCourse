package by.triumgroup.recourse.controller;

import by.triumgroup.recourse.controller.exception.ControllerException;
import by.triumgroup.recourse.entity.BaseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public interface CrudController<E extends BaseEntity<ID>, ID> {

    @GetMapping("{id}")
    E getById(@PathVariable("id") ID id) throws ControllerException;

    @PostMapping
    <S extends E> S create(@Valid @RequestBody S entity) throws ControllerException;

    @PutMapping("{id}")
    <S extends E> S update(@Valid @RequestBody S entity, @PathVariable("id") ID id) throws ControllerException;

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") ID id) throws ControllerException;

}
