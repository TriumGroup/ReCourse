package by.triumgroup.recourse.supplier.entity;

import by.triumgroup.recourse.entity.BaseEntity;

public interface EntitySupplier<E extends BaseEntity<ID>, ID> {
    E getValidEntity();
    E getInvalidEntity();
}
