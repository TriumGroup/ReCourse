package by.triumgroup.recourse.controller.impl;

import by.triumgroup.recourse.controller.DocumentController;
import by.triumgroup.recourse.controller.exception.NotFoundException;
import by.triumgroup.recourse.document.DocumentType;
import by.triumgroup.recourse.document.generator.DocumentGenerator;
import by.triumgroup.recourse.document.model.provider.ContentProvider;
import by.triumgroup.recourse.document.model.provider.StudentProfileContentProvider;
import by.triumgroup.recourse.entity.model.User;
import by.triumgroup.recourse.entity.support.DocumentTypeEnumConverter;
import by.triumgroup.recourse.service.UserService;
import by.triumgroup.recourse.util.DocumentGeneratorCallWrapper;
import org.slf4j.Logger;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static by.triumgroup.recourse.util.ServiceCallWrapper.wrapServiceCall;
import static by.triumgroup.recourse.util.Util.sanitizeFilename;
import static org.slf4j.LoggerFactory.getLogger;


public class DocumentControllerImpl implements DocumentController {

    private static final Logger logger = getLogger(DocumentControllerImpl.class);
    private UserService userService;

    public DocumentControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(DocumentType.class, new DocumentTypeEnumConverter());
    }

    @Override
    public void generateStudentProfile(
            @PathVariable("id") Integer id,
            @RequestParam("type") DocumentType documentType,
            HttpServletResponse response) {
        Optional<User> studentOptional = wrapServiceCall(logger, () -> userService.findById(id));
        if (studentOptional.isPresent()){
            User existingStudent = studentOptional.get();
            dispatchDocumentRequest(
                    response,
                    documentType,
                    existingStudent,
                    new ArrayList<>(existingStudent.getCourses()),
                    new StudentProfileContentProvider());
        } else {
            throw new NotFoundException();
        }

    }

    private <TMainEntity, TTableEntity> void dispatchDocumentRequest(HttpServletResponse response,
                                                                     DocumentType documentType,
                                                                     TMainEntity mainEntity,
                                                                     Collection<TTableEntity> tableEntities,
                                                                     ContentProvider<TMainEntity, TTableEntity> contentProvider) {
        DocumentGenerator<TMainEntity, TTableEntity> documentGenerator = documentType.createGenerator(contentProvider);
        response.setContentType(documentGenerator.getContentType());
        if (documentGenerator.isForceAttachment()) {
            response.setHeader("Content-disposition", "attachment;filename=" + createFilename(mainEntity, contentProvider, documentGenerator));
        }
        DocumentGeneratorCallWrapper.wrapDocumentGeneratorCall(() -> documentGenerator.writeDocument(
                response,
                mainEntity,
                tableEntities));
    }

    private <TMainEntity, TTableEntity> String createFilename(TMainEntity mainEntity, ContentProvider<TMainEntity, TTableEntity> contentProvider, DocumentGenerator<TMainEntity, TTableEntity> documentGenerator) {
        String filename = String.format("%s.%s",
                contentProvider.createFilename(mainEntity),
                documentGenerator.getFileExtension());
        filename = sanitizeFilename(filename);
        return filename;
    }
}
