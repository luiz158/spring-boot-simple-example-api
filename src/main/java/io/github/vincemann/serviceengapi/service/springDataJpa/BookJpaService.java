package io.github.vincemann.serviceengapi.service.springDataJpa;

import io.github.vincemann.generic.crud.lib.service.springDataJpa.BackRefSettingJPACrudService;
import io.github.vincemann.serviceengapi.model.Book;
import io.github.vincemann.serviceengapi.repository.BookRepository;
import io.github.vincemann.serviceengapi.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookJpaService extends BackRefSettingJPACrudService<Book,Long, BookRepository> implements BookService {

    public BookJpaService(BookRepository jpaRepository) {
        super(jpaRepository, Book.class);
    }
}
