package com.siakad.service;

import com.siakad.exception.*;
import com.siakad.model.Course;
import com.siakad.model.Student;
import com.siakad.repository.CourseRepository;
import com.siakad.repository.StudentRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit Test untuk method validateCreditLimit() dan dropCourse() dengan STUB
 */
public class EnrollmentServiceStubTest {

    // === validateCreditLimit() ===
    @Test
    void testValidateCreditLimitWithinLimit() {
        StudentRepository studentRepo = mock(StudentRepository.class);
        CourseRepository courseRepo = mock(CourseRepository.class);
        NotificationService notif = mock(NotificationService.class);
        GradeCalculator gradeCalc = mock(GradeCalculator.class);

        Student s = new Student();
        s.setStudentId("S001");
        s.setGpa(3.8);

        when(studentRepo.findById("S001")).thenReturn(s);
        when(gradeCalc.calculateMaxCredits(3.8)).thenReturn(24);

        EnrollmentService service = new EnrollmentService(studentRepo, courseRepo, notif, gradeCalc);
        boolean result = service.validateCreditLimit("S001", 20);

        assertTrue(result); // 20 <= 24
    }

    @Test
    void testValidateCreditLimitExceedsLimit() {
        StudentRepository studentRepo = mock(StudentRepository.class);
        CourseRepository courseRepo = mock(CourseRepository.class);
        NotificationService notif = mock(NotificationService.class);
        GradeCalculator gradeCalc = mock(GradeCalculator.class);

        Student s = new Student();
        s.setStudentId("S002");
        s.setGpa(2.8);

        when(studentRepo.findById("S002")).thenReturn(s);
        when(gradeCalc.calculateMaxCredits(2.8)).thenReturn(18);

        EnrollmentService service = new EnrollmentService(studentRepo, courseRepo, notif, gradeCalc);
        boolean result = service.validateCreditLimit("S002", 24);

        assertFalse(result); // 24 > 18
    }

    @Test
    void testValidateCreditLimitStudentNotFound() {
        StudentRepository studentRepo = mock(StudentRepository.class);
        CourseRepository courseRepo = mock(CourseRepository.class);
        NotificationService notif = mock(NotificationService.class);
        GradeCalculator gradeCalc = mock(GradeCalculator.class);

        when(studentRepo.findById("S999")).thenReturn(null);

        EnrollmentService service = new EnrollmentService(studentRepo, courseRepo, notif, gradeCalc);
        assertThrows(StudentNotFoundException.class, () ->
                service.validateCreditLimit("S999", 20));
    }

    // === dropCourse() ===
    @Test
    void testDropCourseSuccess() {
        StudentRepository studentRepo = mock(StudentRepository.class);
        CourseRepository courseRepo = mock(CourseRepository.class);
        NotificationService notif = mock(NotificationService.class);
        GradeCalculator gradeCalc = mock(GradeCalculator.class);

        Student s = new Student();
        s.setStudentId("S003");
        s.setEmail("s003@mail.com");

        Course c = new Course();
        c.setCourseCode("CS101");
        c.setCourseName("Pemrograman Java");
        c.setEnrolledCount(20);

        when(studentRepo.findById("S003")).thenReturn(s);
        when(courseRepo.findByCourseCode("CS101")).thenReturn(c);

        EnrollmentService service = new EnrollmentService(studentRepo, courseRepo, notif, gradeCalc);
        service.dropCourse("S003", "CS101");

        verify(courseRepo, times(1)).update(c);
        verify(notif, times(1))
                .sendEmail(eq("s003@mail.com"), anyString(), contains("Pemrograman Java"));
        assertEquals(19, c.getEnrolledCount());
    }

    @Test
    void testDropCourseStudentNotFound() {
        StudentRepository studentRepo = mock(StudentRepository.class);
        CourseRepository courseRepo = mock(CourseRepository.class);
        NotificationService notif = mock(NotificationService.class);
        GradeCalculator gradeCalc = mock(GradeCalculator.class);

        when(studentRepo.findById("S404")).thenReturn(null);

        EnrollmentService service = new EnrollmentService(studentRepo, courseRepo, notif, gradeCalc);
        assertThrows(StudentNotFoundException.class, () ->
                service.dropCourse("S404", "CS101"));
    }

    @Test
    void testDropCourseNotFound() {
        StudentRepository studentRepo = mock(StudentRepository.class);
        CourseRepository courseRepo = mock(CourseRepository.class);
        NotificationService notif = mock(NotificationService.class);
        GradeCalculator gradeCalc = mock(GradeCalculator.class);

        Student s = new Student();
        s.setStudentId("S007");
        s.setEmail("s007@mail.com");

        when(studentRepo.findById("S007")).thenReturn(s);
        when(courseRepo.findByCourseCode("CS999")).thenReturn(null);

        EnrollmentService service = new EnrollmentService(studentRepo, courseRepo, notif, gradeCalc);
        assertThrows(CourseNotFoundException.class, () ->
                service.dropCourse("S007", "CS999"));
    }
}
