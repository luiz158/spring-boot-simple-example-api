package io.github.vincemann.serviceengapi.repository;

import io.github.vincemann.serviceengapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>
{

}
