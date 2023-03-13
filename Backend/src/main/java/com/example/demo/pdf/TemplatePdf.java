package com.example.demo.pdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class TemplatePdf {

    private Font f;

    public TemplatePdf() throws IOException {
        String FONT = "c:/windows/fonts/arial.ttf";
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        f = new Font(bf);
    }

    private void writeTableLogo(PdfPTable table, Image jpg) {
        PdfPCell cell = new PdfPCell();
        cell.setFixedHeight(65);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        f.setSize(25);
        cell.setBorder(0);
        cell.setPaddingBottom(5);
        cell.setPhrase(new Phrase("MAgro", f));
        table.addCell(cell);
        cell.setFixedHeight(65);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setImage(jpg);
        table.addCell(cell);
    }

    public ByteArrayInputStream export() throws DocumentException, IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
        Image jpg = Image.getInstance("logo.png");
        document.open();
        PdfPTable tableLogo = new PdfPTable(2);
        tableLogo.setWidthPercentage(100f);
        tableLogo.setWidths(new float[]{5f, 5f});
        writeTableLogo(tableLogo, jpg);
        document.add(tableLogo);
        body(document);
        document.close();

        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    public void body(Document document) {
    }
}
