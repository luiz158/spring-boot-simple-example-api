package io.github.vincemann.serviceengapi.model.abs;

import io.github.vincemann.generic.crud.lib.model.IdentifiableEntityImpl;
import lombok.*;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person extends IdentifiableEntityImpl<Long> {
    private String firstName;
    private String lastName;
}
