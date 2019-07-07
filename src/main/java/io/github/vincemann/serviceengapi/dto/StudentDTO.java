package io.github.vincemann.serviceengapi.dto;

import io.github.vincemann.serviceengapi.model.abs.Person;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class StudentDTO extends Person {
    private Set<BookDTO> books = new HashSet<>();

    @Builder
    public StudentDTO(String firstName, String lastName, Set<BookDTO> books) {
        super(firstName, lastName);
        if(books==null){
            this.books=new HashSet<>();
        }else {
            this.books = books;
        }
    }
}
