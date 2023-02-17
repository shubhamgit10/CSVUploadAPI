package com.luxoft.test.uploadcsvfile.service;

import com.luxoft.test.uploadcsvfile.helper.CSVFileUploadHelper;
import com.luxoft.test.uploadcsvfile.model.FileUploadModel;
import com.luxoft.test.uploadcsvfile.repository.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

@Service
//@EnableJpaRepositories("com.luxoft.test.uploadcsvfile.repository")
public class FileUploadService {
    @Autowired
     FileUploadRepository fileUploadRepository;
     public void save(MultipartFile file){
         try{
             List<FileUploadModel> uploadFileModels = CSVFileUploadHelper.csvToUplaodFile(file.getInputStream());
             fileUploadRepository.saveAll(uploadFileModels);
         } catch (UnsupportedEncodingException e) {
             throw new RuntimeException(e);
         } catch (IOException e) {
             throw new RuntimeException("Failed to save or store csv file: " +e.getMessage());
         }
     }
     public List<FileUploadModel> getAllUploadedFilesData() {
         return fileUploadRepository.findAll();
     }
     public List<FileUploadModel> getUploadedFilesById(Long id) {
         return fileUploadRepository.findAllById(Collections.singleton(id));
     }

    public void deleteUploadedFilesById(Long id) {
         fileUploadRepository.deleteById(id);
    }
}
