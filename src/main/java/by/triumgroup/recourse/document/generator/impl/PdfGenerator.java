package by.triumgroup.recourse.document.generator.impl;

import by.triumgroup.recourse.document.generator.DocumentGenerator;
import by.triumgroup.recourse.document.model.provider.ContentProvider;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

public class PdfGenerator<TMainModel, TTableEntity> implements DocumentGenerator<TMainModel, TTableEntity> {
    private final static String TEMPLATE_URL = "./template.pdf";

    private static final Font TITLE_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font DEFAULT_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
    private static final Font STRONG_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    private static final Font EM_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.ITALIC);

    private static final String SUBTITLE_SEPARATOR = ": ";

    private static final int MARGIN_LEFT_RIGHT = 40;
    private static final int MARGIN_TOP_BOTTOM = 20;
    private static final int MARGIN_TOP_FIRST_PAGE = 100;

    private ContentProvider<TMainModel, TTableEntity> contentProvider;

    public PdfGenerator(ContentProvider<TMainModel, TTableEntity> contentProvider) {
        this.contentProvider = contentProvider;
    }

    @Override
    public String getContentType() {
        return MediaType.APPLICATION_PDF_VALUE;
    }

    @Override
    public void writeDocument(HttpServletResponse response, TMainModel mainModel, Collection<TTableEntity> tableEntities) throws DocumentException, IOException {
        OutputStream output = response.getOutputStream();

        PdfReader template = openTemplate(TEMPLATE_URL);
        Document document = createDocument(template.getPageSizeWithRotation(1));

        PdfWriter writer = createPdfWriter(output, document);
        setCopyProtection(writer);

        document.open();

        setTemplate(template, writer);
        addTitle(document, contentProvider.createTitle(mainModel));
        addContent(mainModel, tableEntities, document);

        document.close();

        output.flush();
    }

    @Override
    public String getFileExtension() {
        return "pdf";
    }

    @Override
    public boolean isForceAttachment() {
        return false;
    }

    private PdfWriter createPdfWriter(OutputStream output, Document document) throws DocumentException {
        PdfWriter writer = PdfWriter.getInstance(document, output);
        writer.setPageEvent(new PdfNewPageEventHandler());
        return writer;
    }

    private void addTitle(Document document, String title) throws DocumentException {
        Paragraph titleParagraph = new Paragraph(title, TITLE_FONT);
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        document.add(titleParagraph);
    }

    private void addContent(TMainModel mainModel, Collection<TTableEntity> tableEntities, Document document) throws DocumentException {
        addSubtitles(document, mainModel);

        Paragraph tableTitle = createTableTitle(mainModel, tableEntities);
        document.add(tableTitle);

        PdfPTable pdfTable = createTable(tableEntities);
        document.add(pdfTable);
    }

    private void addSubtitles(Document document, TMainModel mainModel) throws DocumentException {
        List<Pair<String, String>> subtitles = contentProvider.createSubtitles(mainModel);
        for (Pair<String, String> pair: subtitles) {
            String name = pair.getFirst();
            String value = pair.getSecond();
            Paragraph paragraph = new Paragraph();
            paragraph.add(new Phrase(name, EM_FONT));
            paragraph.add(new Phrase(SUBTITLE_SEPARATOR, EM_FONT));
            paragraph.add(new Phrase(value, DEFAULT_FONT));
            document.add(paragraph);
        }
    }

    private PdfPTable createTable(Collection<TTableEntity> tableEntities) {
        PdfPTable pdfTable = new PdfPTable(contentProvider.getHeaders().size());
        pdfTable.setWidthPercentage(100);

        for (String header : contentProvider.getHeaders()) {
            PdfPCell headerCell = new PdfPCell(new Phrase(header, STRONG_FONT));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(headerCell);
        }

        for (List<String> row : contentProvider.createRows(tableEntities)) {
            for (String cell : row) {
                pdfTable.addCell(new Paragraph(cell, DEFAULT_FONT));
            }
        }
        return pdfTable;
    }

    private Paragraph createTableTitle(TMainModel mainModel, Collection<TTableEntity> tableEntities) {
        Paragraph tableTitle = new Paragraph(contentProvider.createTableTitle(mainModel, tableEntities), EM_FONT);
        tableTitle.setSpacingAfter(10);
        tableTitle.setAlignment(Element.ALIGN_CENTER);
        return tableTitle;
    }

    private PdfReader openTemplate(String templateUrl) throws IOException{
        return new PdfReader(templateUrl);
    }

    private Document createDocument(Rectangle pageSize) throws IOException {
        Document document = new Document(pageSize);

        document.setMargins(MARGIN_LEFT_RIGHT, MARGIN_LEFT_RIGHT, MARGIN_TOP_FIRST_PAGE, MARGIN_TOP_BOTTOM);
        document.setMarginMirroringTopBottom(true);

        return document;
    }

    private void setTemplate(PdfReader templateReader, PdfWriter writer) {
        PdfContentByte content = writer.getDirectContent();
        PdfImportedPage page = writer.getImportedPage(templateReader, 1);
        content.addTemplate(page, 0, 0);
    }

    private void setCopyProtection(PdfWriter writer) throws DocumentException{
        writer.setEncryption(
                null,
                null,
                ~(PdfWriter.ALLOW_COPY),
                PdfWriter.STANDARD_ENCRYPTION_128);
    }

    private class PdfNewPageEventHandler extends PdfPageEventHelper {
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            document.setMargins(MARGIN_LEFT_RIGHT, MARGIN_LEFT_RIGHT, MARGIN_TOP_BOTTOM, MARGIN_TOP_BOTTOM);
        }
    }
}
