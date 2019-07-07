package io.github.vincemann.serviceengapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.vincemann.generic.crud.lib.entityListener.BiDirChildEntityListener;
import io.github.vincemann.generic.crud.lib.model.IdentifiableEntityImpl;
import io.github.vincemann.generic.crud.lib.model.biDir.BiDirChild;
import io.github.vincemann.generic.crud.lib.model.biDir.BiDirParent;
import io.github.vincemann.generic.crud.lib.model.biDir.BiDirParentEntity;
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
@EntityListeners(BiDirChildEntityListener.class)
public class Lecture extends IdentifiableEntityImpl<Long> implements BiDirChild{

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "lecture_students",
            joinColumns = @JoinColumn(name = "lecture_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();

    @JsonBackReference
    @ManyToOne
    @BiDirParentEntity
    private Professor professor;

    @Builder
    public Lecture(Set<Student> students, Professor professor) {
        if(students==null){
            this.students=new HashSet<>();
        }else {
            this.students = students;
        }
        this.professor=professor;
    }
}
