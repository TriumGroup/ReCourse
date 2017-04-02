package by.triumgroup.recourse.entity.supplier;

import by.triumgroup.recourse.entity.BaseEntity;

public interface EntitySupplier<E extends BaseEntity<ID>, ID> {
    E getValidEntity();
    E getInvalidEntity();
}
