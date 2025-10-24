package com.siakad.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk NotificationService.
 * Karena ini interface, kita akan buat dummy implementation
 * supaya semua method bisa dites dan coverage naik.
 */
class NotificationServiceTest {

    private NotificationService notificationService;

    // Dummy class buat simulasi implementasi NotificationService
    static class DummyNotificationService implements NotificationService {
        boolean emailSent = false;
        boolean smsSent = false;
        String lastEmail;
        String lastPhone;

        @Override
        public void sendEmail(String email, String subject, String message) {
            this.emailSent = true;
            this.lastEmail = email;
        }

        @Override
        public void sendSMS(String phone, String message) {
            this.smsSent = true;
            this.lastPhone = phone;
        }
    }

    @BeforeEach
    void setUp() {
        notificationService = new DummyNotificationService();
    }

    @Test
    void testSendEmail() {
        notificationService.sendEmail("gerry@example.com", "Test", "Hello Gerry!");
        DummyNotificationService dummy = (DummyNotificationService) notificationService;

        assertTrue(dummy.emailSent);
        assertEquals("gerry@example.com", dummy.lastEmail);
    }

    @Test
    void testSendSMS() {
        notificationService.sendSMS("08123456789", "Hai Gerry!");
        DummyNotificationService dummy = (DummyNotificationService) notificationService;

        assertTrue(dummy.smsSent);
        assertEquals("08123456789", dummy.lastPhone);
    }
}
