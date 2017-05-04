package by.triumgroup.recourse.document;

import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.io.OutputStream;

public interface PdfDocumentGenerator {

    void generatePdfById(OutputStream output, Integer id) throws DocumentException, IOException;

}
