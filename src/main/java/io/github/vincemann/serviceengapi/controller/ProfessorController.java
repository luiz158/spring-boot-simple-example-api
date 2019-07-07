package io.github.vincemann.serviceengapi.controller;

import io.github.vincemann.generic.crud.lib.controller.springAdapter.DTOCrudControllerSpringAdapter;
import io.github.vincemann.generic.crud.lib.controller.springAdapter.idFetchingStrategy.IdFetchingStrategy;
import io.github.vincemann.generic.crud.lib.controller.springAdapter.mediaTypeStrategy.MediaTypeStrategy;
import io.github.vincemann.generic.crud.lib.controller.springAdapter.validationStrategy.JavaXValidationStrategy;
import io.github.vincemann.generic.crud.lib.dtoMapper.BasicDTOMapper;
import io.github.vincemann.generic.crud.lib.dtoMapper.DTOMapper;
import io.github.vincemann.generic.crud.lib.service.EndpointService;
import io.github.vincemann.serviceengapi.dto.ProfessorDTO;
import io.github.vincemann.serviceengapi.model.Professor;
import io.github.vincemann.serviceengapi.service.ProfessorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;

@Controller
public class ProfessorController extends DTOCrudControllerSpringAdapter<Professor, ProfessorDTO,Long, ProfessorService> {

    public ProfessorController(ProfessorService crudService,
                               EndpointService endpointService,
                               IdFetchingStrategy<Long> longIdFetchingStrategy,
                               MediaTypeStrategy mediaTypeStrategy) {
        super(crudService, endpointService, Professor.class, ProfessorDTO.class, longIdFetchingStrategy, mediaTypeStrategy,
                new JavaXValidationStrategy<>());
    }


    @Override
    protected DTOMapper<Professor, ProfessorDTO, Long> provideServiceEntityToDTOMapper() {
        return new BasicDTOMapper<>(ProfessorDTO.class,new ModelMapper());
    }

    @Override
    protected DTOMapper<ProfessorDTO, Professor, Long> provideDTOToServiceEntityMapper() {
        return new BasicDTOMapper<>(Professor.class,new ModelMapper());
    }
}
