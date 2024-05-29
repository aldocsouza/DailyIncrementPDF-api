package com.aldo.generate.file.pdf.generateFilePdf.Services;

import com.aldo.generate.file.pdf.generateFilePdf.DTOs.DocumentTableDTO;
import com.aldo.generate.file.pdf.generateFilePdf.DTOs.Mappers.DocumentTableMapper;
import com.aldo.generate.file.pdf.generateFilePdf.Model.DocumentTable;
import com.aldo.generate.file.pdf.generateFilePdf.Repository.DocumentTableRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SchedulingService {

    private DocumentTableRepository tableRepository;
    private HashService hashService;
    private DocumentTableMapper documentTableMapper;

    public SchedulingService(DocumentTableRepository tableRepository, HashService hashService, DocumentTableMapper documentTableMapper) {
        this.tableRepository = tableRepository;
        this.hashService = hashService;
        this.documentTableMapper = documentTableMapper;
    }

    @Scheduled(cron = ("0 0 0 * * *")) // 0(Segundo), 0(Minuto), 0(Hora), 0(Dia), 0(Mês) e 0(Ano)
    //@Scheduled(fixedDelay = 30000) // Aqui você pode escolher os segundos
    public DocumentTableDTO incrementNumberDocument() {
        Integer maxNumber = Optional.ofNullable(tableRepository.findMaxNumber()).orElse(0);
        int newNumber = maxNumber + 1;
        LocalDateTime data = LocalDateTime.now();

        DocumentTable table = new DocumentTable();
        table.setNumber(newNumber);
        table.setDate(data);
        table.setHash(hashService.generateValidationCode(documentTableMapper.toDTO(table)));

        return documentTableMapper.toDTO(tableRepository.save(table));
    }

}
