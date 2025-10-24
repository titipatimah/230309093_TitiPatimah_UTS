package com.siakad.service;

import com.siakad.model.CourseGrade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GradeCalculatorTest {

    private GradeCalculator gradeCalculator;

    @BeforeEach
    void setup() {
        gradeCalculator = new GradeCalculator();
    }

    // 🔹 1. Test hitung GPA dengan data valid
    @Test
    void testCalculateGPA_ValidGrades() {
        List<CourseGrade> grades = Arrays.asList(
                new CourseGrade("CS101", 3, 4.0),
                new CourseGrade("CS102", 3, 3.5),
                new CourseGrade("CS103", 2, 3.0)
        );
        double gpa = gradeCalculator.calculateGPA(grades);
        assertEquals(3.56, gpa, 0.02); // toleransi delta 0.02
    }

    // 🔹 2. Test jika daftar kosong
    @Test
    void testCalculateGPA_EmptyList() {
        double gpa = gradeCalculator.calculateGPA(Collections.emptyList());
        assertEquals(0.0, gpa, 0.0);
    }

    // 🔹 3. Test jika null
    @Test
    void testCalculateGPA_NullList() {
        double gpa = gradeCalculator.calculateGPA(null);
        assertEquals(0.0, gpa, 0.0);
    }

    // 🔹 4. Test jika ada grade point tidak valid
    @Test
    void testCalculateGPA_InvalidGradePoint() {
        List<CourseGrade> grades = List.of(
                new CourseGrade("CS101", 3, 4.5)
        );
        assertThrows(IllegalArgumentException.class,
                () -> gradeCalculator.calculateGPA(grades));
    }

    // 🔹 5. Test jika semua credits = 0
    @Test
    void testCalculateGPA_AllZeroCredits() {
        List<CourseGrade> grades = Arrays.asList(
                new CourseGrade("CS101", 0, 4.0),
                new CourseGrade("CS102", 0, 3.0)
        );
        double gpa = gradeCalculator.calculateGPA(grades);
        assertEquals(0.0, gpa, 0.0);
    }

    // 🔹 6. Test status akademik semester 1–2
    @Test
    void testDetermineAcademicStatus_Semester1_2() {
        assertEquals("ACTIVE", gradeCalculator.determineAcademicStatus(2.5, 1));
        assertEquals("PROBATION", gradeCalculator.determineAcademicStatus(1.8, 2));
    }

    // 🔹 7. Test status akademik semester 3–4
    @Test
    void testDetermineAcademicStatus_Semester3_4() {
        assertEquals("ACTIVE", gradeCalculator.determineAcademicStatus(2.5, 3));
        assertEquals("PROBATION", gradeCalculator.determineAcademicStatus(2.1, 4));
        assertEquals("SUSPENDED", gradeCalculator.determineAcademicStatus(1.5, 4));
    }

    // 🔹 8. Test status akademik semester 5+
    @Test
    void testDetermineAcademicStatus_Semester5Plus() {
        assertEquals("ACTIVE", gradeCalculator.determineAcademicStatus(3.0, 6));
        assertEquals("PROBATION", gradeCalculator.determineAcademicStatus(2.2, 7));
        assertEquals("SUSPENDED", gradeCalculator.determineAcademicStatus(1.8, 8));
    }

    // 🔹 9. Test invalid GPA
    @Test
    void testDetermineAcademicStatus_InvalidGPA() {
        assertThrows(IllegalArgumentException.class,
                () -> gradeCalculator.determineAcademicStatus(-1.0, 2));
        assertThrows(IllegalArgumentException.class,
                () -> gradeCalculator.determineAcademicStatus(4.5, 3));
    }

    // 🔹 10. Test invalid semester
    @Test
    void testDetermineAcademicStatus_InvalidSemester() {
        assertThrows(IllegalArgumentException.class,
                () -> gradeCalculator.determineAcademicStatus(3.0, 0));
    }

    // 🔹 11. Test calculateMaxCredits valid range
    @Test
    void testCalculateMaxCredits_Valid() {
        assertEquals(24, gradeCalculator.calculateMaxCredits(3.5));
        assertEquals(21, gradeCalculator.calculateMaxCredits(2.7));
        assertEquals(18, gradeCalculator.calculateMaxCredits(2.2));
        assertEquals(15, gradeCalculator.calculateMaxCredits(1.5));
    }

    // 🔹 12. Test calculateMaxCredits invalid GPA
    @Test
    void testCalculateMaxCredits_Invalid() {
        assertThrows(IllegalArgumentException.class,
                () -> gradeCalculator.calculateMaxCredits(-0.5));
        assertThrows(IllegalArgumentException.class,
                () -> gradeCalculator.calculateMaxCredits(4.5));
    }
}
