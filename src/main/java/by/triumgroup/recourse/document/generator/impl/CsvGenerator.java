package by.triumgroup.recourse.document.generator.impl;

import by.triumgroup.recourse.document.generator.DocumentGenerator;
import by.triumgroup.recourse.document.model.provider.ContentProvider;
import com.itextpdf.text.DocumentException;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class CsvGenerator<TMainEntity, TTableEntity> implements DocumentGenerator<TMainEntity, TTableEntity> {
    private ContentProvider<TMainEntity, TTableEntity> contentProvider;

    public CsvGenerator(ContentProvider<TMainEntity, TTableEntity> contentProvider) {
        this.contentProvider = contentProvider;
    }

    @Override
    public String getContentType() {
        return "text/csv; charset=utf-8";
    }

    @Override
    public String getFileExtension() {
        return "csv";
    }

    @Override
    public boolean isForceAttachment() {
        return true;
    }

    @Override
    public void writeDocument(HttpServletResponse response, TMainEntity mainModel, Collection<TTableEntity> tableEntities) throws DocumentException, IOException {
        ICsvListWriter listWriter = new CsvListWriter(response.getWriter(),
                CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
        addContent(tableEntities, listWriter);
        listWriter.flush();
    }

    private void addContent(Collection<TTableEntity> tableEntities, ICsvListWriter listWriter) throws IOException {
        listWriter
                .writeHeader(
                        contentProvider
                                .getHeaders()
                                .toArray(
                                        new String[contentProvider
                                                .getHeaders()
                                                .size()]));


        List<List<String>> modelList = contentProvider.createRows(tableEntities);
        for (List<String> row : modelList) {
            listWriter.write(row);
        }
    }
}
