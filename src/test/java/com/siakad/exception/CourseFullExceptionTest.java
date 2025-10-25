package com.siakad.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk CourseFullException.
 * Tujuan: pastikan kedua constructor bekerja dengan benar.
 */
class CourseFullExceptionTest {

    @Test
    void testConstructorWithMessage() {
        CourseFullException ex = new CourseFullException("Kelas penuh");
        assertEquals("Kelas penuh", ex.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        Throwable cause = new IllegalStateException("Penyebab error");
        CourseFullException ex = new CourseFullException("Kelas penuh", cause);

        assertEquals("Kelas penuh", ex.getMessage());
        assertEquals(cause, ex.getCause());
        assertTrue(true);
    }
}
