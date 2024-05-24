package com.aldo.generate.file.pdf.generateFilePdf.Repository;

import com.aldo.generate.file.pdf.generateFilePdf.Model.DocumentTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentTableRepository extends JpaRepository<DocumentTable, Long> {
    @Query("SELECT MAX(number) FROM DocumentTable")
    Integer findMaxNumber();

    List<DocumentTable> findByNumber(Integer number);
}
