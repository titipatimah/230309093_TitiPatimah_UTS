package com.siakad.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk class Course.
 * Tujuan: pastikan semua getter, setter, dan addPrerequisite berfungsi dengan benar.
 */
class CourseTest {

    private Course course;

    @BeforeEach
    void setUp() {
        course = new Course("IF101", "Pemrograman Dasar", 3, 30, 10, "Bu Sinta");
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("IF101", course.getCourseCode());
        assertEquals("Pemrograman Dasar", course.getCourseName());
        assertEquals(3, course.getCredits());
        assertEquals(30, course.getCapacity());
        assertEquals(10, course.getEnrolledCount());
        assertEquals("Bu Sinta", course.getLecturer());
        assertNotNull(course.getPrerequisites());
    }

    @Test
    void testSetters() {
        course.setCourseCode("IF102");
        course.setCourseName("Struktur Data");
        course.setCredits(4);
        course.setCapacity(40);
        course.setEnrolledCount(20);
        course.setLecturer("Pak Dimas");

        assertEquals("IF102", course.getCourseCode());
        assertEquals("Struktur Data", course.getCourseName());
        assertEquals(4, course.getCredits());
        assertEquals(40, course.getCapacity());
        assertEquals(20, course.getEnrolledCount());
        assertEquals("Pak Dimas", course.getLecturer());
    }

    @Test
    void testSetAndGetPrerequisites() {
        List<String> prereq = Arrays.asList("IF001", "IF002");
        course.setPrerequisites(prereq);

        assertEquals(2, course.getPrerequisites().size());
        assertTrue(course.getPrerequisites().contains("IF001"));
        assertTrue(course.getPrerequisites().contains("IF002"));
    }

    @Test
    void testAddPrerequisite() {
        course.addPrerequisite("IF201");
        assertTrue(course.getPrerequisites().contains("IF201"));
    }

    @Test
    void testAddPrerequisite_WhenListIsNull() {
        // Simulasikan list null untuk uji kondisi if (this.prerequisites == null)
        course.setPrerequisites(null);
        course.addPrerequisite("IF301");

        assertNotNull(course.getPrerequisites());
        assertEquals(1, course.getPrerequisites().size());
        assertTrue(course.getPrerequisites().contains("IF301"));
    }
}
