package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.CrudController;
import by.triumgroup.recourse.controller.CrudControllerTest;
import by.triumgroup.recourse.controller.LessonController;
import by.triumgroup.recourse.entity.model.Lesson;
import by.triumgroup.recourse.service.CrudService;
import by.triumgroup.recourse.service.HometaskService;
import by.triumgroup.recourse.service.LessonService;
import by.triumgroup.recourse.supplier.entity.model.EntitySupplier;
import by.triumgroup.recourse.supplier.entity.model.impl.LessonSupplier;
import org.mockito.Mockito;

public class LessonControllerTest extends CrudControllerTest<Lesson, Integer> {

    private LessonController lessonController;
    private LessonService lessonService;
    private LessonSupplier lessonSupplier;
    private HometaskService hometaskService;

    public LessonControllerTest() {
        lessonService = Mockito.mock(LessonService.class);
        hometaskService = Mockito.mock(HometaskService.class);
        lessonController = new LessonControllerImpl(lessonService, hometaskService);
        lessonSupplier = new LessonSupplier();
    }

    @Override
    protected CrudController<Lesson, Integer> getController() {
        return lessonController;
    }

    @Override
    protected CrudService<Lesson, Integer> getService() {
        return lessonService;
    }

    @Override
    protected String getEntityName() {
        return "lesson";
    }

    @Override
    protected EntitySupplier<Lesson, Integer> getEntitySupplier() {
        return lessonSupplier;
    }
}
