package com.aldo.generate.file.pdf.generateFilePdf.Services;

import com.aldo.generate.file.pdf.generateFilePdf.Model.DocumentTable;
import com.aldo.generate.file.pdf.generateFilePdf.Repository.DocumentTableRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class SchedulingService {


    private DocumentTableRepository tableRepository;

    public SchedulingService(DocumentTableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    //@Scheduled(cron = ("0 48 15 * * *"))
    @Scheduled(fixedDelay = 10000)
    public DocumentTable incrementNumberDocument() {
        Integer maxNumber = Optional.ofNullable(tableRepository.findMaxNumber()).orElse(0);
        int newNumber = maxNumber + 1;
        LocalDateTime now = LocalDateTime.now();

        DocumentTable table = new DocumentTable();
        table.setNumber(newNumber);
        table.setDate(now);

        return tableRepository.save(table);
    }

}
