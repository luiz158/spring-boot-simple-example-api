package io.github.vincemann.serviceengapi.service.springDataJpa;

import io.github.vincemann.generic.crud.lib.service.springDataJpa.BackRefSettingJPACrudService;
import io.github.vincemann.serviceengapi.model.Lecture;
import io.github.vincemann.serviceengapi.repository.LectureRepository;
import io.github.vincemann.serviceengapi.service.LectureService;
import org.springframework.stereotype.Service;

@Service
public class LectureJpaService extends BackRefSettingJPACrudService<Lecture,Long, LectureRepository> implements LectureService {

    public LectureJpaService(LectureRepository jpaRepository) {
        super(jpaRepository, Lecture.class);
    }
}
