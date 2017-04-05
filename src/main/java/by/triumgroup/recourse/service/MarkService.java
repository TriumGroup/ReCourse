package by.triumgroup.recourse.service;

import by.triumgroup.recourse.entity.Mark;

public interface MarkService extends CrudService<Mark, Integer> {

    Mark findBySolutionId(Integer id);

}
