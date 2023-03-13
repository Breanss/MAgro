package com.example.demo.pdf;

import com.example.demo.entity.Field;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class FieldPdfExporter extends TemplatePdf {
    private Font f;
    private ArrayList<Field> listFields;

    public FieldPdfExporter(ArrayList<Field> listFields) throws IOException {
        super();
        this.listFields = listFields;
        String FONT = "c:/windows/fonts/arial.ttf";
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        f = new Font(bf);
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.getHSBColor(136, 34, 29));
        cell.setPaddingBottom(5);


        cell.setPhrase(new Phrase("L.p", f));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Nazwa własna", f));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Numer ewidencyjny", f));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Argonomia gleby", f));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Forma posiadania", f));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Obszar", f));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        int licznik = 1;
        float sumArea = 0.00f;
        float sumRent = 0.00f;
        float sumOwn = 0.00f;
        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);

        for (Field field : listFields) {
            cell.setPhrase(new Phrase(Integer.toString(licznik) + ".", f));
            table.addCell(cell);
            cell.setPhrase(new Phrase(field.getName(), f));
            table.addCell(cell);
            cell.setPhrase(new Phrase(field.getNumberRegistration(), f));
            table.addCell(cell);
            cell.setPhrase(new Phrase(field.getArgonomicCategory(), f));
            table.addCell(cell);
            cell.setPhrase(new Phrase(field.getProperty(), f));
            table.addCell(cell);
            cell.setPhrase(new Phrase(String.format("%.2f", field.getArea()) + " ha", f));
            table.addCell(cell);

            if (field.getProperty().equals("Dzierżawa")) {
                sumRent += field.getArea().doubleValue();
            } else if (field.getProperty().equals("Własność")) {
                sumOwn += field.getArea().doubleValue();
            }

            sumArea += field.getArea().doubleValue();
            licznik++;
        }

        cell.setColspan(5);
        cell.setPhrase(new Phrase("Suma obszaru gruntów własnych", f));
        table.addCell(cell);
        cell.setPhrase(new Phrase(String.format("%.2f", sumOwn) + " ha", f));
        table.addCell(cell);
        cell.setColspan(5);
        cell.setPhrase(new Phrase("Suma obszaru gruntów dzierżaw", f));
        table.addCell(cell);
        cell.setPhrase(new Phrase(String.format("%.2f", sumRent) + " ha", f));
        table.addCell(cell);
        cell.setColspan(5);
        cell.setPhrase(new Phrase("Suma obszaru gruntów wszystkich", f));
        table.addCell(cell);
        cell.setPhrase(new Phrase(String.format("%.2f", sumArea) + " ha", f));
        table.addCell(cell);
    }


    public void body(Document document) {
        f.setSize(18);
        Paragraph p = new Paragraph("Lista pól", f);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{0.7f, 1.8f, 2.2f, 1.9f, 2.2f, 1f});
        table.setSpacingBefore(10);
        f.setSize(11);
        f.setColor(Color.WHITE);
        writeTableHeader(table);
        f.setColor(Color.BLACK);
        writeTableData(table);
        document.add(table);
    }
}
