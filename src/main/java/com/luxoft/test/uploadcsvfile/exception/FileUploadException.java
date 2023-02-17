package com.luxoft.test.uploadcsvfile.exception;

import com.luxoft.test.uploadcsvfile.response.FileUploadResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FileUploadException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity handleException(MaxUploadSizeExceededException e) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new FileUploadResponse("File size exceeded 5MB pls upload file with less than 5 MB"));
    }

}
