package com.luxoft.test.uploadcsvfile;

import com.luxoft.test.uploadcsvfile.model.FileUploadModel;
import com.luxoft.test.uploadcsvfile.repository.FileUploadRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class FileUploadTest {

    @Test
    public void fails_to_return_data_if_repo_is_empty(){
        Iterable results =fileUploadRepository.findAll();
        assertThat(results).isEmpty();
    }

    @Test
    public void it_save_uploaded_csv_file() {
       FileUploadModel results = fileUploadRepository.save(new FileUploadModel(101,"Test", "Desc", null));
        assertThat(results).hasFieldOrPropertyWithValue("name", "Test");
        assertThat(results).hasFieldOrPropertyWithValue("description", "Desc");

    }

    @Test
    public void it_returns_uploaded_files_by_id() {
        FileUploadModel res1 = new FileUploadModel(101, "name", "desc4",null);
        FileUploadModel res2 = new FileUploadModel(102, "name2", "desc5", null);
        entityManager.persist(res2);
        FileUploadModel result = fileUploadRepository.findById(res2.getId()).get();
        assertThat(result).isEqualTo(res2);
    }

    @Test
    public void it_delete_files_by_id() {
        FileUploadModel res1 = new FileUploadModel(102, "Test", "Desciption1",null);
        FileUploadModel res2 = new FileUploadModel(103, "Test2", "desc3", null);

        fileUploadRepository.deleteById(res2.getId());
        Iterable results = fileUploadRepository.findAll();
        assertThat(results).hasSize(1).contains(res1);

    }
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private FileUploadRepository fileUploadRepository;
}
