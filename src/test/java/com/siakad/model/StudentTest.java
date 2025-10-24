package com.siakad.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk class Student.
 * Tujuan: memastikan semua konstruktor, getter, dan setter bekerja dengan benar.
 */
class StudentTest {

    @Test
    void testConstructorAndGetters() {
        Student student = new Student(
                "STU001",
                "Gerry",
                "gerry@example.com",
                "Rekayasa Keamanan Siber",
                5,
                3.5,
                "ACTIVE"
        );

        assertEquals("STU001", student.getStudentId());
        assertEquals("Gerry", student.getName());
        assertEquals("gerry@example.com", student.getEmail());
        assertEquals("Rekayasa Keamanan Siber", student.getMajor());
        assertEquals(5, student.getSemester());
        assertEquals(3.5, student.getGpa());
        assertEquals("ACTIVE", student.getAcademicStatus());
    }

    @Test
    void testDefaultConstructorAndSetters() {
        Student student = new Student();

        student.setStudentId("STU002");
        student.setName("Alya");
        student.setEmail("alya@example.com");
        student.setMajor("Teknik Informatika");
        student.setSemester(3);
        student.setGpa(2.75);
        student.setAcademicStatus("PROBATION");

        assertAll(
                () -> assertEquals("STU002", student.getStudentId()),
                () -> assertEquals("Alya", student.getName()),
                () -> assertEquals("alya@example.com", student.getEmail()),
                () -> assertEquals("Teknik Informatika", student.getMajor()),
                () -> assertEquals(3, student.getSemester()),
                () -> assertEquals(2.75, student.getGpa()),
                () -> assertEquals("PROBATION", student.getAcademicStatus())
        );
    }

    @Test
    void testUpdateValues() {
        Student student = new Student();
        student.setStudentId("STU010");
        student.setName("Budi");
        student.setEmail("budi@kampus.ac.id");
        student.setMajor("Sistem Informasi");
        student.setSemester(6);
        student.setGpa(1.8);
        student.setAcademicStatus("SUSPENDED");

        assertEquals("STU010", student.getStudentId());
        assertEquals("Budi", student.getName());
        assertEquals("budi@kampus.ac.id", student.getEmail());
        assertEquals("Sistem Informasi", student.getMajor());
        assertEquals(6, student.getSemester());
        assertEquals(1.8, student.getGpa());
        assertEquals("SUSPENDED", student.getAcademicStatus());
    }
}
