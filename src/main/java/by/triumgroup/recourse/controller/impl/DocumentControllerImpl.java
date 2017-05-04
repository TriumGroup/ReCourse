package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.DocumentController;
import by.triumgroup.recourse.document.impl.StudentProfilePdfGenerator;
import by.triumgroup.recourse.service.exception.ServiceException;
import com.itextpdf.text.DocumentException;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.triumgroup.recourse.util.ServiceCallWrapper.wrapServiceCall;
import static org.slf4j.LoggerFactory.getLogger;


public class DocumentControllerImpl implements DocumentController {

    private static final Logger logger = getLogger(DocumentControllerImpl.class);
    private final StudentProfilePdfGenerator studentProfilePdfGenerator;

    public DocumentControllerImpl(StudentProfilePdfGenerator studentProfilePdfGenerator) {
        this.studentProfilePdfGenerator = studentProfilePdfGenerator;
    }

    @Override
    public void generateStudentProfile(
            @PathVariable("id") Integer id,
            HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        wrapServiceCall(logger, () -> {
            try {
                ServletOutputStream stream = response.getOutputStream();
                studentProfilePdfGenerator.generatePdfById(stream, id);
            } catch (IOException | DocumentException e) {
                throw new ServiceException(e);
            }
        });
    }
}
