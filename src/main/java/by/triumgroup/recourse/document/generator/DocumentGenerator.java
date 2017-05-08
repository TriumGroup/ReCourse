package by.triumgroup.recourse.document.generator;

import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

public interface DocumentGenerator<TMainEntity, TTableEntity> {
    void writeDocument(OutputStream output,
                       TMainEntity mainModel,
                       Collection<TTableEntity> tableEntities) throws DocumentException, IOException;
}
