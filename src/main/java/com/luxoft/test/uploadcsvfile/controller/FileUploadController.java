package com.luxoft.test.uploadcsvfile.controller;

import com.luxoft.test.uploadcsvfile.helper.CSVFileUploadHelper;
import com.luxoft.test.uploadcsvfile.model.FileUploadModel;
import com.luxoft.test.uploadcsvfile.response.FileUploadResponse;
import com.luxoft.test.uploadcsvfile.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.logging.Logger;

@RestController
@Validated
@RequestMapping("/api/csv-upload")
public class FileUploadController {
    @Autowired
    private FileUploadService uploadFileService;

    @PostMapping("/upload-file")

    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        Logger.getLogger("Controller method to load the csv file");
        String message = "";
        if(CSVFileUploadHelper.hasCSVFormat(file)) {
            try {
                uploadFileService.save(file);
                message = "File uploaded successfully: " +file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new FileUploadResponse(message));
            }
            catch (Exception e){
                message = "Unable to uplaod the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileUploadResponse(message));
            }
        }
        message = "File is not csv type pls upload a csv file";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FileUploadResponse(message));
    }

        @GetMapping("/uploaded-files")
    public ResponseEntity<List<FileUploadModel>> getUploadedFiles(){
        try {
List<FileUploadModel> uploadFileModelList = uploadFileService.getAllUploadedFilesData();
if (uploadFileModelList.isEmpty()){
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
return new ResponseEntity<>(uploadFileModelList, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/uploaded-files/{id}")
    public ResponseEntity<List<FileUploadModel>> getUploadedFilesById(@PathVariable Long id){
        try {
            List<FileUploadModel> uploadFileModelList = uploadFileService.getUploadedFilesById(id);
            if (uploadFileModelList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(uploadFileModelList, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<FileUploadModel>> deleteUploadedFileById(@PathVariable Long id){
        try {
             uploadFileService.deleteUploadedFilesById(id);
            if (id==null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
