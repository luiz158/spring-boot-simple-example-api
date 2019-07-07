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
public class Professor extends Person implements BiDirParent {

    @JsonManagedReference
    @OneToMany(mappedBy = "professor",cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
    @BiDirChildCollection(Lecture.class)
    private Set<Lecture> lectures = new HashSet<>();

    @Builder
    public Professor(String firstName, String lastName, Set<Lecture> lectures) {
        super(firstName, lastName);
        if(lectures==null){
            this.lectures=new HashSet<>();
        }else {
            this.lectures = lectures;
        }
    }
}
