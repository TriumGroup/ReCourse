package by.triumgroup.recourse.document.model.mapper;

import by.triumgroup.recourse.entity.model.BaseEntity;

import java.util.List;

public interface EntityToRowMapper<ID, E extends BaseEntity<ID>> {
    List<String> toRow(E entity);
}
