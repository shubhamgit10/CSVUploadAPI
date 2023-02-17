package com.luxoft.test.uploadcsvfile.helper;

import com.luxoft.test.uploadcsvfile.model.FileUploadModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CSVFileUploadHelper {
    public static String TYPE = "text/csv";
    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals((file.getContentType()))) {
            return false;
        }
        return true;
    }

    public static List<FileUploadModel> csvToUplaodFile(InputStream input) throws UnsupportedEncodingException {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<FileUploadModel> uploadFileModels = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                FileUploadModel uploadFileModel = new FileUploadModel(
                        Long.parseLong(csvRecord.get("Id")),
                        csvRecord.get("Name"),
                        csvRecord.get("Description"),
                        Date.valueOf(csvRecord.get("UpdatedAt")));

                uploadFileModels.add(uploadFileModel);
            }

            return uploadFileModels;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse the CSV file: " + e.getMessage());
        }
    }
}