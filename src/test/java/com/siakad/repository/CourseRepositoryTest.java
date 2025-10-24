package com.siakad.repository;

import com.siakad.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk interface CourseRepository.
 * Karena ini interface, kita buat dummy class untuk menguji default method-nya.
 */
class CourseRepositoryTest {

    private CourseRepository repository;

    // Dummy class untuk menguji default method
    static class DummyCourseRepository implements CourseRepository {}

    @BeforeEach
    void setUp() {
        repository = new DummyCourseRepository();
    }

    @Test
    void testFindByCourseCode_DefaultReturnsNull() {
        Course result = repository.findByCourseCode("IF101");
        assertNull(result, "Default method harus mengembalikan null");
    }

    @Test
    void testUpdate_DefaultDoesNothing() {
        // Panggil method untuk memastikan tidak lempar exception
        assertDoesNotThrow(() -> repository.update(new Course()));
    }

    @Test
    void testIsPrerequisiteMet_DefaultReturnsFalse() {
        boolean result = repository.isPrerequisiteMet("STU001", "IF101");
        assertFalse(result, "Default method harus mengembalikan false");
    }

    @Test
    void testCustomImplementationOverride() {
        // Simulasi implementasi baru untuk memastikan override bisa jalan
        CourseRepository customRepo = new CourseRepository() {
            @Override
            public boolean isPrerequisiteMet(String studentId, String courseCode) {
                return true;
            }
        };

        assertTrue(customRepo.isPrerequisiteMet("STU001", "IF101"));
    }
}
