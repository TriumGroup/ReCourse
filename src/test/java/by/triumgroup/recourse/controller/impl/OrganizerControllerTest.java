package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.AbstractControllerTest;
import by.triumgroup.recourse.controller.OrganizerController;
import by.triumgroup.recourse.service.CourseService;
import org.mockito.Mockito;

public class OrganizerControllerTest extends AbstractControllerTest {
    private OrganizerController organizerController;
    private CourseService courseService;

    public OrganizerControllerTest() {
        courseService = Mockito.mock(CourseService.class);
        organizerController = new OrganizerControllerImpl(courseService);
    }

    @Override
    protected Object getController() {
        return organizerController;
    }
}
