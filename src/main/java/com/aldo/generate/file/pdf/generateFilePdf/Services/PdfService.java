package com.aldo.generate.file.pdf.generateFilePdf.Services;

import com.aldo.generate.file.pdf.generateFilePdf.DTOs.DocumentTableDTO;
import com.aldo.generate.file.pdf.generateFilePdf.DTOs.Mappers.DocumentTableMapper;
import com.aldo.generate.file.pdf.generateFilePdf.Model.DocumentTable;
import com.aldo.generate.file.pdf.generateFilePdf.Repository.DocumentTableRepository;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PdfService {

    private final DocumentTableRepository documentTableRepository;
    private final DocumentTableMapper documentTableMapper;

    public PdfService(DocumentTableRepository documentTableRepository, DocumentTableMapper documentTableMapper) {
        this.documentTableRepository = documentTableRepository;
        this.documentTableMapper = documentTableMapper;
    }

    public byte[] generatePdf() {
        try {
                Integer dt = documentTableRepository.findMaxNumber();
                List<DocumentTable> find = documentTableRepository.findByNumber(dt);
                DocumentTableDTO doct = documentTableMapper.toDTO(find.get(0));

                BufferedImage barcodeImage = BarCodeService.generateCode128BarcodeImage(doct.hash());

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
                table.addCell("Numero da Guia: " + doct.number());
                table.addCell("Descrição: ");
                table.addCell("Data de Emissão: " + doct.date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
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
                table.setMarginBottom(90);
                document.add(table);

                ByteArrayOutputStream imageBaos = new ByteArrayOutputStream();
                ImageIO.write(barcodeImage, "png", imageBaos);
                Image barcodePdfImage = new Image(com.itextpdf.io.image.ImageDataFactory.create(imageBaos.toByteArray()));
                document.add(barcodePdfImage);
                document.add(new Paragraph(doct.hash()).setMargins(20, 10, 10, 185));



                document.close();

                return byteArrayOutputStream.toByteArray();

        } catch (Exception e) {
                throw new RuntimeException(e);
        }
    }

}
