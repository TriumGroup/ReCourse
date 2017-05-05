package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.configuration.security.UserAuthDetails;
import by.triumgroup.recourse.controller.CourseFeedbackController;
import by.triumgroup.recourse.controller.exception.BadRequestException;
import by.triumgroup.recourse.controller.exception.NotFoundException;
import by.triumgroup.recourse.entity.model.CourseFeedback;
import by.triumgroup.recourse.service.CourseFeedbackService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Objects;

import static by.triumgroup.recourse.util.ServiceCallWrapper.wrapServiceCall;
import static org.slf4j.LoggerFactory.getLogger;

public class CourseFeedbackControllerImpl
        extends AbstractCrudController<CourseFeedback, Integer>
        implements CourseFeedbackController {

    private static final Logger logger = getLogger(CourseFeedbackControllerImpl.class);
    private final CourseFeedbackService courseFeedbackService;

    public CourseFeedbackControllerImpl(CourseFeedbackService courseFeedbackService) {
        super(courseFeedbackService, logger);
        this.courseFeedbackService = courseFeedbackService;
    }

    @Override
    protected void validateNestedEntities(CourseFeedback entity) {
        if (entity.getStudent().getId() == null) {
            throw new BadRequestException("student.id", "Student ID is not specified");
        }
    }

    @Override
    protected boolean hasAuthorityToEdit(CourseFeedback entity, UserAuthDetails authDetails) {
        return Objects.equals(entity.getStudent().getId(), authDetails.getId());
    }

    @Override
    public CourseFeedback findByCourseIdAndStudentId(@PathVariable("courseId") Integer courseId, @PathVariable("studentId") Integer studentId) {
        return wrapServiceCall(logger, () -> courseFeedbackService
                .findByCourseIdAndStudentId(courseId, studentId)
                .orElseThrow(NotFoundException::new));
    }
}
