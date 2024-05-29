package com.aldo.generate.file.pdf.generateFilePdf.DTOs;

import java.time.LocalDateTime;

public record DocumentTableDTO (
        Long id,
        Integer number,
        LocalDateTime date,
        String hash
) {
}
