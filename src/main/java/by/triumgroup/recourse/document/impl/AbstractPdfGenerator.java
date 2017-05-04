package by.triumgroup.recourse.document.impl;

import by.triumgroup.recourse.document.PdfDocumentGenerator;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.io.OutputStream;

public abstract class AbstractPdfGenerator implements PdfDocumentGenerator {

    protected static final Font CAT_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    protected static final Font RED_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    protected static final Font SUB_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    protected static final Font SMALL_BOLD = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    private static final String IMAGE_PATH = "src/main/resources/logo.jpg";

    protected static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    @Override
    public final void generatePdfById(OutputStream output, Integer id) throws DocumentException, IOException {
        loadDataById(id);
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, output);
        document.open();
        addMetaData(document);
        addTitle(document);
        addContent(document);
        addBackgroundLogo(writer);
        document.close();
    }

    protected abstract void loadDataById(Integer id);

    protected abstract void addMetaData(Document document);

    protected abstract void addTitle(Document document);

    protected abstract void addContent(Document document);

    protected void addBackgroundLogo(PdfWriter writer) throws DocumentException, IOException {
        PdfContentByte canvas = writer.getDirectContentUnder();
        Image image = Image.getInstance(IMAGE_PATH);
        image.scaleAbsolute(PageSize.A4.getWidth(), PageSize.A4.getWidth());
        image.setAbsolutePosition(0, 0);
        canvas.saveState();
        PdfGState state = new PdfGState();
        state.setFillOpacity(0.6f);
        canvas.setGState(state);
        canvas.addImage(image);
        canvas.restoreState();
    }

}
