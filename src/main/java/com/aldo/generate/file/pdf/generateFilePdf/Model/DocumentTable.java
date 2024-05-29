package com.aldo.generate.file.pdf.generateFilePdf.Model;


import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
public class DocumentTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_document", nullable = false)
    private Integer number;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "emission_date", nullable = false)
    private LocalDateTime date;

    @Column(name = "hash", nullable = false)
    private String hash;

    public DocumentTable(Integer number, LocalDateTime date, String hash) {
        this.number = number;
        this.date = date;
        this.hash = hash;
    }

    public DocumentTable() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getHash(){
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
