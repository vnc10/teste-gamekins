package gamekins.project.service;

import gamekins.project.domain.Student;
import gamekins.project.repository.CourseRepository;
import gamekins.project.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import gamekins.project.domain.dto.StudentDTO;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void shouldThrowExceptionWhenCourseNotFound() {

        Long studentId = 1L;
        Long invalidCourseId = 99L;


        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setCourseId(invalidCourseId);
        studentDTO.setName("John Doe");


        Student existingStudent = new Student();
        existingStudent.setId(studentId);

        when(studentRepository.findById(studentId))
                .thenReturn(Optional.of(existingStudent));


        when(courseRepository.findById(invalidCourseId))
                .thenReturn(Optional.empty());


        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            studentService.update(studentId, studentDTO);
        });

        assertEquals("Course not found", exception.getMessage());


        verify(studentRepository).findById(studentId);
        verify(courseRepository).findById(invalidCourseId);

        verify(studentRepository, never()).save(any());
    }
}