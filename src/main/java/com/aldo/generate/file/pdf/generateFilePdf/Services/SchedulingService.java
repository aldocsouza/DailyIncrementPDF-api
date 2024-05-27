package com.aldo.generate.file.pdf.generateFilePdf.Services;

import com.aldo.generate.file.pdf.generateFilePdf.Model.DocumentTable;
import com.aldo.generate.file.pdf.generateFilePdf.Repository.DocumentTableRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchedulingService {

    private DocumentTableRepository tableRepository;
    private HashService hashService;

    public SchedulingService(DocumentTableRepository tableRepository, HashService hashService) {
        this.tableRepository = tableRepository;
        this.hashService = hashService;
    }

    //@Scheduled(cron = ("0 0 0 * * *")) // 0(Segundo), 0(Minuto), 0(Hora), 0(Dia), 0(Mês) e 0(Ano)
    @Scheduled(fixedDelay = 30000) // Aqui você pode escolher os segundos
    public DocumentTable incrementNumberDocument() {
        Integer maxNumber = Optional.ofNullable(tableRepository.findMaxNumber()).orElse(0);
        int newNumber = maxNumber + 1;
        LocalDateTime data = LocalDateTime.now();

        DocumentTable table = new DocumentTable();
        table.setNumber(newNumber);
        table.setDate(data);
        table.setHash(hashService.generateValidationCode(table));

        return tableRepository.save(table);
    }

}
