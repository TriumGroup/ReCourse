package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.LessonController;
import by.triumgroup.recourse.controller.exception.ControllerException;
import by.triumgroup.recourse.controller.exception.NotFoundException;
import by.triumgroup.recourse.entity.Hometask;
import by.triumgroup.recourse.entity.Lesson;
import by.triumgroup.recourse.service.HometaskService;
import by.triumgroup.recourse.service.LessonService;
import by.triumgroup.recourse.service.exception.ServiceException;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class LessonControllerImpl
        extends AbstractCrudController<Lesson, Integer>
        implements LessonController {

    private static final Logger logger = getLogger(LessonControllerImpl.class);
    private final HometaskService hometaskService;

    public LessonControllerImpl(LessonService lessonService, HometaskService hometaskService) {
        super(lessonService, logger);
        this.hometaskService = hometaskService;
    }

    @Override
    public Hometask getHometask(@PathVariable("lessonId") Integer lessonId, Pageable pageable) {
        try {
            Optional<Hometask> entity = hometaskService.findByLessonId(lessonId);
            return entity.orElseThrow(NotFoundException::new);
        } catch (ServiceException e) {
            logger.warn("Error while retrieving hometask for lesson with id " + lessonId + "\n", e);
            throw new ControllerException(e);
        }
    }
}
