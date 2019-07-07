package io.github.vincemann.serviceengapi.dto;

import io.github.vincemann.generic.crud.lib.bidir.BiDirDTOChild;
import io.github.vincemann.generic.crud.lib.model.IdentifiableEntityImpl;
import io.github.vincemann.generic.crud.lib.model.biDir.BiDirParentId;
import io.github.vincemann.serviceengapi.model.Student;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class BookDTO extends IdentifiableEntityImpl<Long> implements BiDirDTOChild<Long> {

    @BiDirParentId(Student.class)
    private Long studentId;
    private String name;
    private Long isbn;

    @Builder
    public BookDTO(Long studentId, String name, Long isbn) {
        this.studentId = studentId;
        this.name = name;
        this.isbn = isbn;
    }
}
