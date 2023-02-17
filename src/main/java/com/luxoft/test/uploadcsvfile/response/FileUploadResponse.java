package com.luxoft.test.uploadcsvfile.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileUploadResponse {
    private String message;

    public FileUploadResponse(String message){
        this.message=message;
    }
}
