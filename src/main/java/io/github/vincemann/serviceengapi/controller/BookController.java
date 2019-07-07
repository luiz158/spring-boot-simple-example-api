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
import io.github.vincemann.serviceengapi.dto.BookDTO;
import io.github.vincemann.serviceengapi.model.Book;
import io.github.vincemann.serviceengapi.model.Student;
import io.github.vincemann.serviceengapi.service.BookService;
import io.github.vincemann.serviceengapi.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collections;

@Controller
public class BookController extends DTOCrudControllerSpringAdapter<Book, BookDTO,Long, BookService> {

    @Autowired
    private StudentService studentService;

    public BookController(BookService crudService,
                          EndpointService endpointService,
                          IdFetchingStrategy<Long> longIdFetchingStrategy,
                          MediaTypeStrategy mediaTypeStrategy) {
        super(crudService, endpointService,Book.class, BookDTO.class, longIdFetchingStrategy, mediaTypeStrategy,
                new JavaXValidationStrategy<>());
    }

    @Override
    protected DTOMapper<Book, BookDTO, Long> provideServiceEntityToDTOMapper() {
        ModelMapper modelMapper = new ModelMapper();
        //map backref to id
        modelMapper.createTypeMap(Book.class, BookDTO.class)
                .<Long>addMapping(book -> book.getStudent().getId(), BookDTO::setStudentId);
        return new BasicDTOMapper<>(BookDTO.class,modelMapper);
    }

    @Override
    protected DTOMapper<BookDTO, Book, Long> provideDTOToServiceEntityMapper() {
        //student parent id auflösen zu Student Parent Service Entity
        BackRefResolvingConverter<BookDTO, Book,Student,Long,Long> backRefResolvingConverter =
                new BackRefResolvingConverter(studentService,Student.class,BookDTO.class,Book.class);
        return new BackRefResolvingDTOMapper<BookDTO,Book,Long>(Book.class, Collections.singletonList(backRefResolvingConverter));
    }
}
