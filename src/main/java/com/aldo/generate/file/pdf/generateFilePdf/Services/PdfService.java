package com.aldo.generate.file.pdf.generateFilePdf.Services;

import com.aldo.generate.file.pdf.generateFilePdf.Model.DocumentTable;
import com.aldo.generate.file.pdf.generateFilePdf.Repository.DocumentTableRepository;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PdfService {

    private DocumentTableRepository documentTableRepository;

    public PdfService(DocumentTableRepository documentTableRepository) {
        this.documentTableRepository = documentTableRepository;
    }

    public byte[] generatePdf() {

        Integer dt = documentTableRepository.findMaxNumber();
        List<DocumentTable> find = documentTableRepository.findByNumber(dt);
        DocumentTable doct = find.get(0);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        PageSize pageSize = PageSize.A4.rotate();
        pdfDoc.setDefaultPageSize(pageSize);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("SISTEMA - Exemplo de Tabela"));

        Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
        table.setMarginTop(5);
        table.addCell("Nome: ");
        table.addCell("Numero da Guia: " + doct.getNumber());
        table.addCell("Descrição: ");
        table.addCell("Data de Emissão: " + doct.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        table.addCell("Value");
        table.addCell("Hexadecimal: ");
        document.add(table);

        table = new Table(UnitValue.createPercentArray(5)).useAllAvailableWidth();
        table.setMarginTop(15);
        table.addCell(" ").setMinHeight(100);
        table.addCell(" ").setMinHeight(100);
        table.addCell(" ").setMinHeight(100);
        table.addCell(" ").setMinHeight(100);
        table.addCell(" ").setMinHeight(100);

        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");

        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");

        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");

        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        table.addCell(" ");
        document.add(table);

        document.add(new Paragraph(doct.getHash()).setMargins(250, 10, 10, 185));

        document.close();

        return byteArrayOutputStream.toByteArray();
    }

}
