package gamekins.project.mapper;

import gamekins.project.domain.Subject;
import gamekins.project.domain.dto.SubjectDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SubjectMapperTest {

    @Test
    void shouldCorrectlyMapSubjectEntityToSubjectDTO() {

        Subject subjectEntity = new Subject();
        subjectEntity.setId(1L);
        subjectEntity.setName("test");


        SubjectDTO resultDTO = SubjectMapper.toDTO(subjectEntity);

        assertNotNull(resultDTO);

        assertEquals(subjectEntity.getId(), resultDTO.getId());
        assertEquals(subjectEntity.getName(), resultDTO.getName());
        assertEquals(subjectEntity.getCode(), resultDTO.getCode());
    }

}
