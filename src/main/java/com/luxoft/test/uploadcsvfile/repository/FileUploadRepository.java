package com.luxoft.test.uploadcsvfile.repository;

import com.luxoft.test.uploadcsvfile.model.FileUploadModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileUploadRepository extends JpaRepository<FileUploadModel, Long> {

    Optional<FileUploadModel> findById(Long id);
}
