package io.github.vincemann.serviceengapi.service;

import io.github.vincemann.generic.crud.lib.service.CrudService;
import io.github.vincemann.serviceengapi.model.Book;
import org.springframework.stereotype.Service;

public interface BookService extends CrudService<Book,Long> {
}
