package io.github.vincemann.serviceengapi.service.springDataJpa;

import io.github.vincemann.generic.crud.lib.service.springDataJpa.BackRefSettingJPACrudService;
import io.github.vincemann.serviceengapi.model.Professor;
import io.github.vincemann.serviceengapi.repository.ProfessorRepository;
import io.github.vincemann.serviceengapi.service.ProfessorService;
import org.springframework.stereotype.Service;

@Service
public class ProfessorJpaService extends BackRefSettingJPACrudService<Professor,Long, ProfessorRepository> implements ProfessorService {

    public ProfessorJpaService(ProfessorRepository jpaRepository) {
        super(jpaRepository, Professor.class);
    }
}
