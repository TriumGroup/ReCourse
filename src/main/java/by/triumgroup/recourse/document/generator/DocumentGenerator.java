package by.triumgroup.recourse.document.generator;

import com.itextpdf.text.DocumentException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public interface DocumentGenerator<TMainEntity, TTableEntity> {
    void writeDocument(HttpServletResponse response,
                       TMainEntity mainModel,
                       Collection<TTableEntity> tableEntities) throws DocumentException, IOException;
    String getContentType();
    String getFileExtension();
    boolean isForceAttachment();
}
