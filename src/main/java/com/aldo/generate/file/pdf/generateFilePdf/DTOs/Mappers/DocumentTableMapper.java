package com.aldo.generate.file.pdf.generateFilePdf.DTOs.Mappers;

import com.aldo.generate.file.pdf.generateFilePdf.DTOs.DocumentTableDTO;
import com.aldo.generate.file.pdf.generateFilePdf.Model.DocumentTable;
import org.springframework.stereotype.Component;

@Component
public class DocumentTableMapper {

    public DocumentTable toEntity(DocumentTableDTO documentTableDTO){
        DocumentTable entity = new DocumentTable();
        entity.setId(documentTableDTO.id());
        entity.setNumber(documentTableDTO.number());
        entity.setDate(documentTableDTO.date());
        entity.setHash(documentTableDTO.hash());
        return entity;
    }

    public DocumentTableDTO toDTO(DocumentTable entity){
        DocumentTableDTO dto = new DocumentTableDTO(entity.getId(), entity.getNumber(), entity.getDate(), entity.getHash());
        return dto;
    }

}
