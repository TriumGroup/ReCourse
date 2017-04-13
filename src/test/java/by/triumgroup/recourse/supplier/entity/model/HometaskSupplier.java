package by.triumgroup.recourse.supplier.entity.model;

import by.triumgroup.recourse.entity.model.BaseEntity;
import by.triumgroup.recourse.entity.model.Hometask;

public class HometaskSupplier implements EntityIntegerPKSupplier<Hometask> {
    @Override
    public Hometask getValidEntityWithoutId() {
        Hometask hometask = new Hometask();
        hometask.setLessonId(getAnyId());
        hometask.setTask("task");
        return hometask;
    }

    @Override
    public Hometask getInvalidEntity() {
        return new Hometask();
    }
}
