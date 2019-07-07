package io.github.vincemann.serviceengapi.repository;

import io.github.vincemann.serviceengapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
