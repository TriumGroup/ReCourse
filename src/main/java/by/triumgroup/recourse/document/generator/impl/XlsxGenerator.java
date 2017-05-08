package by.triumgroup.recourse.document.generator.impl;

import by.triumgroup.recourse.document.generator.DocumentGenerator;
import by.triumgroup.recourse.document.model.provider.ContentProvider;
import com.itextpdf.text.DocumentException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class XlsxGenerator<TMainEntity, TTableEntity> implements DocumentGenerator<TMainEntity, TTableEntity> {

    private ContentProvider<TMainEntity, TTableEntity> contentProvider;

    public XlsxGenerator(ContentProvider<TMainEntity, TTableEntity> contentProvider){
        this.contentProvider = contentProvider;
    }

    @Override
    public void writeDocument(HttpServletResponse response, TMainEntity mainModel, Collection<TTableEntity> tableEntities) throws DocumentException, IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Test");
        sheet.setDefaultColumnWidth(30);
        PrintSetup ps = sheet.getPrintSetup();

        sheet.setAutobreaks(true);

        ps.setFitHeight((short)1);
        ps.setFitWidth((short)1);
        sheet.setFitToPage(true);

        CellStyle style = getCellStyle(workbook);
        addContent(tableEntities, contentProvider, sheet, style);

        workbook.write(response.getOutputStream());
    }

    @Override
    public String getContentType() {
        return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    }

    @Override
    public String getFileExtension() {
        return "xlsx";
    }

    @Override
    public boolean isForceAttachment() {
        return true;
    }

    private void addContent(Collection<TTableEntity> tableEntities, ContentProvider<TMainEntity, TTableEntity> contentProvider, Sheet sheet, CellStyle style) {
        Row header = sheet.createRow(0);

        for (int i = 0; i < contentProvider.getHeaders().size(); i++) {
            String caption = contentProvider.getHeaders().get(i);
            header.createCell(i).setCellValue(caption);
            header.getCell(i).setCellStyle(style);
        }


        int rowCount = 1;

        for (List<String> row : contentProvider.createRows(tableEntities)) {
            Row tableRow = sheet.createRow(rowCount++);

            for (int i = 0; i < row.size(); i++) {
                String item = row.get(i);
                tableRow
                        .createCell(i)
                        .setCellValue(item);
                tableRow.getCell(i).setCellStyle(style);
            }
        }

        for (int i = 0; i < contentProvider.getHeaders().size(); i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private CellStyle getCellStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        return style;
    }

}

