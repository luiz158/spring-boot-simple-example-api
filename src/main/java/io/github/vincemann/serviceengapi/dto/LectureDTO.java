package io.github.vincemann.serviceengapi.dto;

import io.github.vincemann.generic.crud.lib.bidir.BiDirDTOChild;
import io.github.vincemann.generic.crud.lib.model.IdentifiableEntityImpl;
import io.github.vincemann.generic.crud.lib.model.biDir.BiDirParentId;
import io.github.vincemann.serviceengapi.model.Professor;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class LectureDTO extends IdentifiableEntityImpl<Long> implements BiDirDTOChild<Long> {
    private Set<StudentDTO> students = new HashSet<>();
    @BiDirParentId(Professor.class)
    private Long professorId;

    @Builder
    public LectureDTO(Set<StudentDTO> students, Long professorId) {
        if(students==null){
            this.students= new HashSet<>();
        }else {
            this.students = students;
        }
        this.professorId = professorId;
    }
}
