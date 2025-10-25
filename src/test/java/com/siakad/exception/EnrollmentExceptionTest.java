package com.siakad.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk EnrollmentException.
 * Tujuan: memastikan kedua constructor bekerja dengan benar.
 */
class EnrollmentExceptionTest {

    @Test
    void testConstructorWithMessage() {
        EnrollmentException ex = new EnrollmentException("Gagal mendaftar mata kuliah");
        assertEquals("Gagal mendaftar mata kuliah", ex.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        Throwable cause = new IllegalArgumentException("Data tidak valid");
        EnrollmentException ex = new EnrollmentException("Kesalahan pendaftaran", cause);

        assertEquals("Kesalahan pendaftaran", ex.getMessage());
        assertEquals(cause, ex.getCause());
        assertTrue(true);
    }
}
