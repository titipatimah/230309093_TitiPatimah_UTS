package com.siakad.repository;

import com.siakad.model.Course;
import com.siakad.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk class StudentRepository.
 * Tujuan: memastikan semua method dapat dipanggil dan hasil default sesuai.
 */
class StudentRepositoryTest {

    private StudentRepository repository;

    @BeforeEach
    void setUp() {
        repository = new StudentRepository();
    }

    @Test
    void testFindById_DefaultReturnsNull() {
        Student result = repository.findById("STU001");
        assertNull(result, "Default method harus mengembalikan null");
    }

    @Test
    void testUpdate_DoesNotThrowException() {
        Student student = new Student("STU002", "Gerry", "gerry@example.com",
                "RKS", 5, 3.5, "ACTIVE");

        assertDoesNotThrow(() -> repository.update(student),
                "Method update() seharusnya tidak melempar exception");
    }

    @Test
    void testGetCompletedCourses_DefaultReturnsNull() {
        List<Course> courses = repository.getCompletedCourses("STU003");
        assertNull(courses, "Default method harus mengembalikan null");
    }

    @Test
    void testCustomImplementationOverride() {
        // Simulasi subclass yang mengubah perilaku method
        StudentRepository customRepo = new StudentRepository() {
            @Override
            public Student findById(String studentId) {
                return new Student("STU999", "Alya", "alya@example.com",
                        "TI", 4, 3.2, "ACTIVE");
            }

            @Override
            public List<Course> getCompletedCourses(String studentId) {
                return List.of(new Course("IF101", "PBO", 3, 30, 0, "Pak Budi"));
            }
        };

        Student found = customRepo.findById("STU999");
        assertNotNull(found);
        assertEquals("Alya", found.getName());

        List<Course> completed = customRepo.getCompletedCourses("STU999");
        assertEquals(1, completed.size());
        assertEquals("IF101", completed.get(0).getCourseCode());
    }
}
