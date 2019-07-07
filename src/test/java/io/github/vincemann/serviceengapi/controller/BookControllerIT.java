package io.github.vincemann.serviceengapi.controller;

import io.github.vincemann.generic.crud.lib.test.controller.springAdapter.UrlParamIdDTOCrudControllerSpringAdapterIT;
import io.github.vincemann.serviceengapi.dto.BookDTO;
import io.github.vincemann.serviceengapi.model.Book;
import io.github.vincemann.serviceengapi.model.Student;
import io.github.vincemann.serviceengapi.service.BookService;
import io.github.vincemann.serviceengapi.service.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment =
        SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerIT extends UrlParamIdDTOCrudControllerSpringAdapterIT<Book, BookDTO, BookService, BookController, Long> {


    @Autowired
    private StudentService studentService;
    private Student testStudent;


    public BookControllerIT(@Autowired BookController crudController) {
        super(crudController, 99L);
    }

    @BeforeEach
    @Override
    public void before() throws Exception {
        this.testStudent = studentService.save(Student.builder().firstName("max").lastName("muster").build());
        super.before();
    }

    @Override
    protected List<BookDTO> provideValidTestDTOs() {
        return Arrays.asList(
                //add book to existing student
                BookDTO.builder()
                        .isbn(1234L)
                        .name("duden")
                        .studentId(testStudent.getId())
                        .build()
        );
    }

    @Override
    protected void modifyTestEntity(BookDTO testEntityDTO) {
        testEntityDTO.setIsbn(999999999999L);
    }

    @AfterEach
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        studentService.delete(testStudent);
    }
}
