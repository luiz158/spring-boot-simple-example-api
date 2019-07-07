package io.github.vincemann.serviceengapi.controller;

import io.github.vincemann.generic.crud.lib.controller.springAdapter.DTOCrudControllerSpringAdapter;
import io.github.vincemann.generic.crud.lib.controller.springAdapter.idFetchingStrategy.IdFetchingStrategy;
import io.github.vincemann.generic.crud.lib.controller.springAdapter.mediaTypeStrategy.MediaTypeStrategy;
import io.github.vincemann.generic.crud.lib.controller.springAdapter.validationStrategy.JavaXValidationStrategy;
import io.github.vincemann.generic.crud.lib.dtoMapper.BasicDTOMapper;
import io.github.vincemann.generic.crud.lib.dtoMapper.DTOMapper;
import io.github.vincemann.generic.crud.lib.dtoMapper.backRefResolving.BackRefResolvingConverter;
import io.github.vincemann.generic.crud.lib.dtoMapper.backRefResolving.BackRefResolvingDTOMapper;
import io.github.vincemann.generic.crud.lib.service.EndpointService;
import io.github.vincemann.serviceengapi.dto.LectureDTO;
import io.github.vincemann.serviceengapi.model.Lecture;
import io.github.vincemann.serviceengapi.model.Professor;
import io.github.vincemann.serviceengapi.service.LectureService;
import io.github.vincemann.serviceengapi.service.ProfessorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collections;

@Controller
public class LectureController extends DTOCrudControllerSpringAdapter<Lecture, LectureDTO,Long, LectureService> {

    @Autowired
    private ProfessorService professorService;

    public LectureController(LectureService crudService,
                          EndpointService endpointService,
                          IdFetchingStrategy<Long> longIdFetchingStrategy,
                          MediaTypeStrategy mediaTypeStrategy) {
        super(crudService, endpointService, Lecture.class, LectureDTO.class, longIdFetchingStrategy, mediaTypeStrategy,
                new JavaXValidationStrategy<>());
    }

    @Override
    protected DTOMapper<Lecture, LectureDTO, Long> provideServiceEntityToDTOMapper() {
        ModelMapper modelMapper = new ModelMapper();
        //map backref to id
        modelMapper.createTypeMap(Lecture.class, LectureDTO.class)
                .<Long>addMapping(lecture -> lecture.getProfessor().getId(), LectureDTO::setProfessorId);
        return new BasicDTOMapper<>(LectureDTO.class,modelMapper);
    }

    @Override
    protected DTOMapper<LectureDTO, Lecture, Long> provideDTOToServiceEntityMapper() {
        //student parent id auflösen zu Student Parent Service Entity
        BackRefResolvingConverter<LectureDTO, Lecture, Professor,Long,Long> backRefResolvingConverter =
                new BackRefResolvingConverter(professorService,Professor.class,LectureDTO.class,Lecture.class);
        return new BackRefResolvingDTOMapper<LectureDTO,Lecture,Long>(Lecture.class, Collections.singletonList(backRefResolvingConverter));
    }
}
