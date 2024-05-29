package com.aldo.generate.file.pdf.generateFilePdf.Services;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

@Service
public class BarCodeService {

    private static final Font BARCODE_TEXT_FONT = new Font("Serif", Font.PLAIN, 14);

    public static BufferedImage generateCode128BarcodeImage(String barcodeText) throws Exception {
        System.out.println(barcodeText);
        Barcode barcode = BarcodeFactory.createCode128(barcodeText);
        System.out.println(barcode);
        barcode.setFont(BARCODE_TEXT_FONT);

        return BarcodeImageHandler.getImage(barcode);
    }


}
