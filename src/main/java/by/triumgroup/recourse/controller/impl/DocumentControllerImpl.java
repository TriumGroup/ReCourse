package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.DocumentController;
import by.triumgroup.recourse.document.DocumentType;
import by.triumgroup.recourse.document.generator.DocumentGenerator;
import by.triumgroup.recourse.document.model.provider.StudentProfileContentProvider;
import by.triumgroup.recourse.entity.model.Course;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.service.exception.ServiceException;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static by.triumgroup.recourse.util.ServiceCallWrapper.wrapServiceCall;
import static org.slf4j.LoggerFactory.getLogger;


public class DocumentControllerImpl implements DocumentController {

    private static final Logger logger = getLogger(DocumentControllerImpl.class);
    private UserService userService;

    public DocumentControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void generateStudentProfile(
            @PathVariable("id") Integer id,
            HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        wrapServiceCall(logger, () -> {
            try {
                ServletOutputStream stream = response.getOutputStream();
                User user = userService.findById(31).get(); // Mikhail_Snitavets@triumgroup.com
                DocumentGenerator<User, Course> studentProfileGenerator = DocumentType.PDF.createGenerator(new StudentProfileContentProvider());
                studentProfileGenerator.writeDocument(stream, user, new ArrayList<>(user.getCourses()));
            } catch (IOException e) {
                throw new ServiceException(e);
            }
        });
    }
}
