package io.github.vincemann.serviceengapi.controller;

import io.github.vincemann.generic.crud.lib.controller.springAdapter.DTOCrudControllerSpringAdapter;
import io.github.vincemann.generic.crud.lib.controller.springAdapter.idFetchingStrategy.IdFetchingStrategy;
import io.github.vincemann.generic.crud.lib.controller.springAdapter.mediaTypeStrategy.MediaTypeStrategy;
import io.github.vincemann.generic.crud.lib.controller.springAdapter.validationStrategy.JavaXValidationStrategy;
import io.github.vincemann.generic.crud.lib.dtoMapper.BasicDTOMapper;
import io.github.vincemann.generic.crud.lib.dtoMapper.DTOMapper;
import io.github.vincemann.generic.crud.lib.service.EndpointService;
import io.github.vincemann.serviceengapi.dto.StudentDTO;
import io.github.vincemann.serviceengapi.model.Student;
import io.github.vincemann.serviceengapi.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;

@Controller
public class StudentController extends DTOCrudControllerSpringAdapter<Student, StudentDTO,Long, StudentService> {

public StudentController(StudentService crudService,
        EndpointService endpointService,
        IdFetchingStrategy<Long> longIdFetchingStrategy,
        MediaTypeStrategy mediaTypeStrategy) {
        super(crudService, endpointService, Student.class, StudentDTO.class, longIdFetchingStrategy, mediaTypeStrategy,
        new JavaXValidationStrategy<>());
        }


@Override
protected DTOMapper<Student, StudentDTO, Long> provideServiceEntityToDTOMapper() {
        return new BasicDTOMapper<>(StudentDTO.class,new ModelMapper());
        }

@Override
protected DTOMapper<StudentDTO, Student, Long> provideDTOToServiceEntityMapper() {
        return new BasicDTOMapper<>(Student.class,new ModelMapper());
        }
}
