package com.siakad.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk class CourseGrade
 * Tujuan: pastikan semua konstruktor, getter, dan setter bekerja dengan benar
 */
class CourseGradeTest {

    @Test
    void testConstructorAndGetters() {
        CourseGrade grade = new CourseGrade("IF101", 3, 4.0);
        assertEquals("IF101", grade.getCourseCode());
        assertEquals(3, grade.getCredits());
        assertEquals(4.0, grade.getGradePoint());
    }

    @Test
    void testDefaultConstructorAndSetters() {
        CourseGrade grade = new CourseGrade();

        grade.setCourseCode("IF202");
        grade.setCredits(2);
        grade.setGradePoint(3.5);

        assertEquals("IF202", grade.getCourseCode());
        assertEquals(2, grade.getCredits());
        assertEquals(3.5, grade.getGradePoint());
    }

    @Test
    void testChangeValues() {
        CourseGrade grade = new CourseGrade("IF303", 4, 2.5);

        grade.setCourseCode("IF404");
        grade.setCredits(5);
        grade.setGradePoint(1.0);

        assertEquals("IF404", grade.getCourseCode());
        assertEquals(5, grade.getCredits());
        assertEquals(1.0, grade.getGradePoint());
    }
}
