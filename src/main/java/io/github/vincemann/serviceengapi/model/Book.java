package io.github.vincemann.serviceengapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.vincemann.generic.crud.lib.entityListener.BiDirChildEntityListener;
import io.github.vincemann.generic.crud.lib.model.IdentifiableEntityImpl;
import io.github.vincemann.generic.crud.lib.model.biDir.BiDirChild;
import io.github.vincemann.generic.crud.lib.model.biDir.BiDirParentEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
@NoArgsConstructor
@EntityListeners(BiDirChildEntityListener.class)
public class Book extends IdentifiableEntityImpl<Long> implements BiDirChild {

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    @BiDirParentEntity
    private Student student;

    private String name;
    private Long isbn;

    @Builder
    public Book(Student student) {
        this.student = student;
    }
}
