package io.github.vincemann.serviceengapi.dto;

import io.github.vincemann.serviceengapi.model.abs.Person;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class ProfessorDTO extends Person {
    private Set<LectureDTO> lectures = new HashSet<>();

    @Builder
    public ProfessorDTO(String firstName, String lastName, Set<LectureDTO> lectures) {
        super(firstName, lastName);
        if(lectures==null){
            this.lectures= new HashSet<>();
        }else {
            this.lectures = lectures;
        }
    }
}
