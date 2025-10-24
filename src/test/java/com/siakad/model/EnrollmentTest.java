package com.siakad.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk class Enrollment.
 * Tujuan: memastikan konstruktor, getter, dan setter berfungsi dengan benar.
 */
class EnrollmentTest {

    @Test
    void testConstructorAndGetters() {
        LocalDateTime now = LocalDateTime.now();

        Enrollment enrollment = new Enrollment(
                "ENR001",
                "STU001",
                "IF101",
                now,
                "APPROVED"
        );

        assertEquals("ENR001", enrollment.getEnrollmentId());
        assertEquals("STU001", enrollment.getStudentId());
        assertEquals("IF101", enrollment.getCourseCode());
        assertEquals(now, enrollment.getEnrollmentDate());
        assertEquals("APPROVED", enrollment.getStatus());
    }

    @Test
    void testDefaultConstructorAndSetters() {
        LocalDateTime time = LocalDateTime.of(2025, 10, 24, 10, 0);

        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentId("ENR002");
        enrollment.setStudentId("STU002");
        enrollment.setCourseCode("IF202");
        enrollment.setEnrollmentDate(time);
        enrollment.setStatus("PENDING");

        assertEquals("ENR002", enrollment.getEnrollmentId());
        assertEquals("STU002", enrollment.getStudentId());
        assertEquals("IF202", enrollment.getCourseCode());
        assertEquals(time, enrollment.getEnrollmentDate());
        assertEquals("PENDING", enrollment.getStatus());
    }

    @Test
    void testChangeValues() {
        Enrollment enrollment = new Enrollment();
        LocalDateTime date = LocalDateTime.now();

        enrollment.setEnrollmentId("ENR010");
        enrollment.setStudentId("STU010");
        enrollment.setCourseCode("IF303");
        enrollment.setEnrollmentDate(date);
        enrollment.setStatus("REJECTED");

        assertAll(
                () -> assertEquals("ENR010", enrollment.getEnrollmentId()),
                () -> assertEquals("STU010", enrollment.getStudentId()),
                () -> assertEquals("IF303", enrollment.getCourseCode()),
                () -> assertEquals(date, enrollment.getEnrollmentDate()),
                () -> assertEquals("REJECTED", enrollment.getStatus())
        );
    }
}
