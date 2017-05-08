package by.triumgroup.recourse.util;

import by.triumgroup.recourse.controller.exception.ControllerException;
import by.triumgroup.recourse.util.WrapperFunctions.DocumentGeneratorCall;
import com.itextpdf.text.DocumentException;

import java.io.IOException;

public class DocumentGeneratorCallWrapper {
    public static void wrapDocumentGeneratorCall(DocumentGeneratorCall documentGeneratorCall) {
        try {
            documentGeneratorCall.call();
        } catch (IOException | DocumentException e) {
            throw new ControllerException(e);
        }
    }
}
