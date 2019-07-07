package io.github.vincemann.serviceengapi.controller;

import io.github.vincemann.generic.crud.lib.controller.exception.EntityMappingException;
import io.github.vincemann.generic.crud.lib.test.controller.springAdapter.UrlParamIdDTOCrudControllerSpringAdapterIT;
import io.github.vincemann.serviceengapi.dto.LectureDTO;
import io.github.vincemann.serviceengapi.dto.ProfessorDTO;
import io.github.vincemann.serviceengapi.model.Lecture;
import io.github.vincemann.serviceengapi.model.Professor;
import io.github.vincemann.serviceengapi.service.ProfessorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment =
        SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfessorControllerIT extends UrlParamIdDTOCrudControllerSpringAdapterIT<Professor, ProfessorDTO, ProfessorService, ProfessorController, Long> {

    @Autowired
    private LectureController lectureController;
    private Lecture testLecture;

    public ProfessorControllerIT(@Autowired ProfessorController crudController) {
        super(crudController, 99L);
    }

    @Override
    @BeforeEach
    public void before() throws Exception {
        this.testLecture = lectureController.getCrudService().save(Lecture.builder().build());
        super.before();
    }

    @Override
    protected List<ProfessorDTO> provideValidTestDTOs() {
        return Arrays.asList(
                //create professor without lectures
                ProfessorDTO.builder()
                .firstName("test")
                .lastName("prof")
                .build()
                /*ProfessorDTO.builder()
                .firstName("test")
                .lastName("prof with lectures")
                .lectures(
                        new HashSet<>(
                                Collections.singleton(
                                        LectureDTO.builder()
                                                .build()
                                )
                        )
                )
                .build()*/
        );
    }

    @Override
    protected void modifyTestEntity(ProfessorDTO testEntityDTO) {
        try {
            LectureDTO lectureDTO = lectureController.provideServiceEntityToDTOMapper().map(testLecture);
            lectureDTO.setProfessorId(testEntityDTO.getId());
            testEntityDTO.setLectures(Collections.singleton(lectureDTO));
        } catch (EntityMappingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    @AfterEach
    public void tearDown() throws Exception {
        lectureController.getCrudService().deleteById(testLecture.getId());
        super.tearDown();
    }
}
