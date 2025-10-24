package com.siakad.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk CourseNotFoundException.
 * Tujuan: memastikan kedua constructor bekerja dengan benar.
 */
class CourseNotFoundExceptionTest {

    @Test
    void testConstructorWithMessage() {
        CourseNotFoundException ex = new CourseNotFoundException("Mata kuliah tidak ditemukan");
        assertEquals("Mata kuliah tidak ditemukan", ex.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        Throwable cause = new NullPointerException("Data null");
        CourseNotFoundException ex = new CourseNotFoundException("Mata kuliah tidak ditemukan", cause);

        assertEquals("Mata kuliah tidak ditemukan", ex.getMessage());
        assertEquals(cause, ex.getCause());
        assertTrue(ex instanceof RuntimeException);
    }
}
