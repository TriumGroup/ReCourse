package by.triumgroup.recourse.document.generator;

import java.io.OutputStream;
import java.util.List;

public interface DocumentGenerator<TMainEntity, TTableEntity> {
    void writeDocument(OutputStream output,
                       TMainEntity mainModel,
                       List<TTableEntity> tableEntities);
}
