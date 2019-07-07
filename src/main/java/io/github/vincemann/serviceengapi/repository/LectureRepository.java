package io.github.vincemann.serviceengapi.repository;

import io.github.vincemann.serviceengapi.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository  extends JpaRepository<Lecture,Long> {
}
