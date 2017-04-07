package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.HometaskSolutionController;
import by.triumgroup.recourse.controller.exception.ControllerException;
import by.triumgroup.recourse.controller.exception.NotFoundException;
import by.triumgroup.recourse.entity.HometaskSolution;
import by.triumgroup.recourse.entity.Mark;
import by.triumgroup.recourse.service.HometaskSolutionService;
import by.triumgroup.recourse.service.MarkService;
import by.triumgroup.recourse.service.exception.ServiceException;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class HometaskSolutionControllerImpl
        extends AbstractCrudController<HometaskSolution, Integer>
        implements HometaskSolutionController {

    private static final Logger logger = getLogger(HometaskSolutionControllerImpl.class);
    private final MarkService markService;

    public HometaskSolutionControllerImpl(HometaskSolutionService hometaskSolutionService, MarkService markService) {
        super(hometaskSolutionService, logger);
        this.markService = markService;
    }

    @Override
    public Mark getMark(@PathVariable("solutionId") Integer solutionId) {
        try {
            Optional<Mark> entity = markService.findBySolutionId(solutionId);
            return entity.orElseThrow(NotFoundException::new);
        } catch (ServiceException e) {
            logger.warn("Error while retrieving mark for solution with id " + solutionId + "\n", e);
            throw new ControllerException(e);
        }
    }
}
