package com.aldo.generate.file.pdf.generateFilePdf.Services;

import com.aldo.generate.file.pdf.generateFilePdf.DTOs.DocumentTableDTO;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.format.DateTimeFormatter;

@Service
public class HashService {

    public String generateValidationCode(DocumentTableDTO documentTableDTO) {
        String concatenation = documentTableDTO.number() + documentTableDTO.date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        String sha256Hash = generateSha256(concatenation);

        return sha256Hash;
    }

    private String generateSha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
