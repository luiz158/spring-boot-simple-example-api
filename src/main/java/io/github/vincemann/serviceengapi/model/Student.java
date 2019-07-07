package io.github.vincemann.serviceengapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.github.vincemann.generic.crud.lib.entityListener.BiDirParentEntityListener;
import io.github.vincemann.generic.crud.lib.model.biDir.BiDirChildCollection;
import io.github.vincemann.generic.crud.lib.model.biDir.BiDirParent;
import io.github.vincemann.serviceengapi.model.abs.Person;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EntityListeners(BiDirParentEntityListener.class)
public class Student extends Person implements BiDirParent {

    @JsonManagedReference
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @BiDirChildCollection(Book.class)
    private Set<Book> books = new HashSet<>();

    @Builder
    public Student(String firstName, String lastName, Set<Book> books) {
        super(firstName, lastName);
        if(books==null) {
            this.books = new HashSet<>();
        }else {
            this.books=books;
        }
    }
}
