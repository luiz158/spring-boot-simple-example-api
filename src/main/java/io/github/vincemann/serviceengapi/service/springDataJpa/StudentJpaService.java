package io.github.vincemann.serviceengapi.service.springDataJpa;

import io.github.vincemann.generic.crud.lib.service.springDataJpa.BackRefSettingJPACrudService;
import io.github.vincemann.serviceengapi.model.Student;
import io.github.vincemann.serviceengapi.repository.StudentRepository;
import io.github.vincemann.serviceengapi.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentJpaService extends BackRefSettingJPACrudService<Student,Long, StudentRepository> implements StudentService {
    public StudentJpaService(StudentRepository jpaRepository) {
        super(jpaRepository, Student.class);
    }
}
