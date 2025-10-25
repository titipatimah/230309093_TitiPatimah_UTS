package com.siakad.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk StudentNotFoundException.
 * Tujuan: memastikan kedua constructor bekerja dengan benar.
 */
class StudentNotFoundExceptionTest {

    @Test
    void testConstructorWithMessage() {
        StudentNotFoundException ex = new StudentNotFoundException("Mahasiswa tidak ditemukan");
        assertEquals("Mahasiswa tidak ditemukan", ex.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        Throwable cause = new RuntimeException("Database error");
        StudentNotFoundException ex = new StudentNotFoundException("Data mahasiswa tidak ada", cause);

        assertEquals("Data mahasiswa tidak ada", ex.getMessage());
        assertEquals(cause, ex.getCause());
        assertTrue(true);
    }
}
