package io.github.vincemann.serviceengapi.controller;

import io.github.vincemann.generic.crud.lib.test.controller.springAdapter.UrlParamIdDTOCrudControllerSpringAdapterIT;
import io.github.vincemann.serviceengapi.dto.StudentDTO;
import io.github.vincemann.serviceengapi.model.Student;
import io.github.vincemann.serviceengapi.service.StudentService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment =
        SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerIT extends UrlParamIdDTOCrudControllerSpringAdapterIT<Student, StudentDTO, StudentService, StudentController, Long> {

    public StudentControllerIT(@Autowired StudentController crudController) {
        super(crudController, 99L);
    }

    @Override
    protected List<StudentDTO> provideValidTestDTOs() {
        return Arrays.asList(
                //create student
                StudentDTO.builder()
                .firstName("test")
                .lastName("student")
                .build()
        );
    }

    @Override
    protected void modifyTestEntity(StudentDTO testEntityDTO) {
        testEntityDTO.setFirstName("MODIFIED");
    }
}
