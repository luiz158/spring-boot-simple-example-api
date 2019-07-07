package io.github.vincemann.serviceengapi.controller;

import io.github.vincemann.generic.crud.lib.controller.exception.EntityMappingException;
import io.github.vincemann.generic.crud.lib.service.exception.BadEntityException;
import io.github.vincemann.generic.crud.lib.service.exception.NoIdException;
import io.github.vincemann.generic.crud.lib.test.controller.springAdapter.UrlParamIdDTOCrudControllerSpringAdapterIT;
import io.github.vincemann.serviceengapi.dto.LectureDTO;
import io.github.vincemann.serviceengapi.dto.StudentDTO;
import io.github.vincemann.serviceengapi.model.Lecture;
import io.github.vincemann.serviceengapi.model.Professor;
import io.github.vincemann.serviceengapi.service.LectureService;
import io.github.vincemann.serviceengapi.service.ProfessorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment =
        SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LectureControllerIT extends UrlParamIdDTOCrudControllerSpringAdapterIT<Lecture, LectureDTO, LectureService, LectureController, Long> {

    @Autowired
    private ProfessorService professorService;

    private Professor testProfessor;

    public LectureControllerIT(@Autowired LectureController crudController) {
        super(crudController, 99L);
    }

    @Override
    @BeforeEach
    public void before() throws Exception {
        testProfessor = professorService.save(Professor.builder().lastName("meier").build());
        super.before();
    }

    @Override
    protected List<LectureDTO> provideValidTestDTOs() {
        return Arrays.asList(
                //create lecture with known professor and 1 student, created on the fly
                LectureDTO.builder()
                        .professorId(testProfessor.getId())
                        .students(
                                new HashSet<>(
                                        Collections.singletonList(
                                                StudentDTO.builder()
                                                        .firstName("peter")
                                                        /*.books(
                                                                new HashSet<>(
                                                                        Collections.singletonList(
                                                                                BookDTO.builder()
                                                                                        .isbn(124361287462L)
                                                                                        .name("duden42")
                                                                                        .build()
                                                                        )

                                                                )

                                                        )*/.build()
                                        )
                                )

                        ).build(),
                //create Lecture with known professor
                LectureDTO.builder()
                        .professorId(testProfessor.getId())
                .build()
        );
    }

    @Test
    public void addStudentToLecture() throws BadEntityException, EntityMappingException, NoIdException {
        //when
        Lecture lecture = Lecture.builder()
                .professor(testProfessor)
                .build();
        Lecture dbLecture = getCrudController().getCrudService().save(lecture);
        Assertions.assertTrue(dbLecture.getStudents().isEmpty());
        StudentDTO studentToAdd = StudentDTO.builder()
                .firstName("i'm")
                .lastName("new to the lecture")
                .build();
        LectureDTO lectureDTO = getCrudController().getServiceEntityToDTOMapper().map(lecture);
        lectureDTO.setStudents(Collections.singleton(studentToAdd));
        //do
        updateEntity(lectureDTO, HttpStatus.OK);
        //then
        Optional<Lecture> optionalLecture = getCrudController().getCrudService().findById(dbLecture.getId());
        Assertions.assertTrue(optionalLecture.isPresent());
        Lecture lectureAfterUpdate = optionalLecture.get();
        Assertions.assertEquals(1,lectureAfterUpdate.getStudents().size());
    }

    @Override
    protected void modifyTestEntity(LectureDTO testEntityDTO) {
        //change students to this single new student
        testEntityDTO.setStudents(Collections.singleton(
                StudentDTO.builder()
                        .firstName("new student")
                        .build())
        );
    }


    /*
    ProfessorDTO.builder()
                .firstName("max")
                .lastName("müller")
                .lectures(
                        new HashSet<>(
                                Arrays.asList(
                                        LectureDTO.builder()
                                                .students(
                                                        new HashSet<>(
                                                                Arrays.asList(
                                                                        StudentDTO.builder()
                                                                                .firstName("peter")
                                                                                .books(
                                                                                        new HashSet<>(
                                                                                                Arrays.asList(
                                                                                                        BookDTO.builder()
                                                                                                                .isbn(124361287462L)
                                                                                                                .name("duden42")
                                                                                                                .build(),
                                                                                                        BookDTO.builder()
                                                                                                                .isbn(4444L)
                                                                                                                .name("another duden")
                                                                                                                .build()
                                                                                                )

                                                                                        )

                                                                                ).build()
                                                                )
                                                        )

                                                ).build()
                                )
                        )
                )
                .build());
     */
}
