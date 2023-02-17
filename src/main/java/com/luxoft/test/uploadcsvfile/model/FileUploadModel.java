package com.luxoft.test.uploadcsvfile.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Getter
@Setter
@Entity
@Table(name = "uploadFile")
public class FileUploadModel {
    @Id
    @Column(name = "id")
    private long id;
    @Column(name ="name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "updatedAt")
    private Date updatedAt;

    public FileUploadModel(long id, String name, String description, Date updatedAt){
        this.id=id;
        this.name=name;
        this.description=description;
        this.updatedAt=updatedAt;
    }

    public FileUploadModel() {

    }
}

