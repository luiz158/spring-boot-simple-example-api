package io.github.vincemann.serviceengapi.service.springDataJpa;

import io.github.vincemann.generic.crud.lib.test.service.CrudServiceTest;
import io.github.vincemann.serviceengapi.model.Lecture;
import io.github.vincemann.serviceengapi.repository.LectureRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment =
        SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LectureJpaServiceTest extends CrudServiceTest<LectureJpaService, Lecture,Long> {

    @Autowired
    private LectureRepository lectureRepository;

    @Override
    protected CrudServiceTestEntry<LectureJpaService, Lecture, Long> provideTestEntity() {
        return new CrudServiceTestEntry<>(new LectureJpaService(lectureRepository),new Lecture());
    }
}
