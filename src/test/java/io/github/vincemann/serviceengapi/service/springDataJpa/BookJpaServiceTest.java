package io.github.vincemann.serviceengapi.service.springDataJpa;

import io.github.vincemann.generic.crud.lib.test.service.CrudServiceTest;
import io.github.vincemann.serviceengapi.model.Book;
import io.github.vincemann.serviceengapi.repository.BookRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@DataJpaTest cant be used because i need autowired components from generic-crud-lib
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment =
        SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookJpaServiceTest extends CrudServiceTest<BookJpaService, Book,Long> {

    @Autowired
    BookRepository bookRepository;

    @Override
    protected CrudServiceTestEntry<BookJpaService,Book,Long> provideTestEntity() {
        return new CrudServiceTestEntry<>(
                new BookJpaService(bookRepository), new Book());
    }
}