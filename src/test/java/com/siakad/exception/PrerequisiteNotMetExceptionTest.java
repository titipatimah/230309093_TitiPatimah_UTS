package com.siakad.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk PrerequisiteNotMetException.
 * Tujuan: memastikan kedua constructor bekerja dengan benar.
 */
class PrerequisiteNotMetExceptionTest {

    @Test
    void testConstructorWithMessage() {
        PrerequisiteNotMetException ex = new PrerequisiteNotMetException("Prasyarat belum terpenuhi");
        assertEquals("Prasyarat belum terpenuhi", ex.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        Throwable cause = new Exception("Error internal");
        PrerequisiteNotMetException ex = new PrerequisiteNotMetException("Tidak memenuhi prasyarat", cause);

        assertEquals("Tidak memenuhi prasyarat", ex.getMessage());
        assertEquals(cause, ex.getCause());
        assertTrue(ex instanceof RuntimeException);
    }
}
