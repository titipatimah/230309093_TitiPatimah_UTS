package com.siakad.service;

import com.siakad.exception.*;
import com.siakad.model.Course;
import com.siakad.model.Enrollment;
import com.siakad.model.Student;
import com.siakad.repository.CourseRepository;
import com.siakad.repository.StudentRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit Test untuk method enrollCourse() dengan MOCK
 */
public class EnrollmentServiceMockTest {

    @Test
    void testEnrollCourseSuccess() {
        // Mock dependencies
        StudentRepository studentRepo = mock(StudentRepository.class);
        CourseRepository courseRepo = mock(CourseRepository.class);
        NotificationService notif = mock(NotificationService.class);
        GradeCalculator gradeCalc = mock(GradeCalculator.class);

        // Data Student
        Student s = new Student();
        s.setStudentId("S001");
        s.setName("Titi");
        s.setEmail("titi@gmail.com");
        s.setGpa(3.8);
        s.setAcademicStatus("ACTIVE");

        // Data Course
        Course c = new Course();
        c.setCourseCode("CS101");
        c.setCourseName("Pemrograman Java");
        c.setCredits(3);
        c.setCapacity(30);
        c.setEnrolledCount(25);
        c.setLecturer("TitiPatimah");

        // Behavior Mock
        when(studentRepo.findById("S001")).thenReturn(s);
        when(courseRepo.findByCourseCode("CS101")).thenReturn(c);
        when(courseRepo.isPrerequisiteMet("S001", "CS101")).thenReturn(true);

        EnrollmentService service = new EnrollmentService(studentRepo, courseRepo, notif, gradeCalc);

        // Eksekusi method
        Enrollment result = service.enrollCourse("S001", "CS101");

        // Verifikasi hasil
        assertNotNull(result);
        assertEquals("S001", result.getStudentId());
        assertEquals("CS101", result.getCourseCode());
        assertEquals("APPROVED", result.getStatus());

        // Verifikasi interaksi mock
        verify(courseRepo, times(1)).update(c);
        verify(notif, times(1))
                .sendEmail(eq("titi@gmail.com"), anyString(), contains("Pemrograman Java"));
    }

    @Test
    void testStudentNotFoundThrowsException() {
        StudentRepository studentRepo = mock(StudentRepository.class);
        CourseRepository courseRepo = mock(CourseRepository.class);
        NotificationService notif = mock(NotificationService.class);
        GradeCalculator gradeCalc = mock(GradeCalculator.class);

        when(studentRepo.findById("S999")).thenReturn(null);

        EnrollmentService service = new EnrollmentService(studentRepo, courseRepo, notif, gradeCalc);
        assertThrows(StudentNotFoundException.class, () ->
                service.enrollCourse("S999", "CS101"));
    }

    @Test
    void testStudentSuspendedThrowsException() {
        StudentRepository studentRepo = mock(StudentRepository.class);
        CourseRepository courseRepo = mock(CourseRepository.class);
        NotificationService notif = mock(NotificationService.class);
        GradeCalculator gradeCalc = mock(GradeCalculator.class);

        Student s = new Student();
        s.setStudentId("S002");
        s.setAcademicStatus("SUSPENDED");

        when(studentRepo.findById("S002")).thenReturn(s);

        EnrollmentService service = new EnrollmentService(studentRepo, courseRepo, notif, gradeCalc);
        assertThrows(EnrollmentException.class, () ->
                service.enrollCourse("S002", "CS101"));
    }

    @Test
    void testCourseNotFoundThrowsException() {
        StudentRepository studentRepo = mock(StudentRepository.class);
        CourseRepository courseRepo = mock(CourseRepository.class);
        NotificationService notif = mock(NotificationService.class);
        GradeCalculator gradeCalc = mock(GradeCalculator.class);

        Student s = new Student();
        s.setStudentId("S003");
        s.setAcademicStatus("ACTIVE");

        when(studentRepo.findById("S003")).thenReturn(s);
        when(courseRepo.findByCourseCode("CS404")).thenReturn(null);

        EnrollmentService service = new EnrollmentService(studentRepo, courseRepo, notif, gradeCalc);
        assertThrows(CourseNotFoundException.class, () ->
                service.enrollCourse("S003", "CS404"));
    }

    @Test
    void testCourseFullThrowsException() {
        StudentRepository studentRepo = mock(StudentRepository.class);
        CourseRepository courseRepo = mock(CourseRepository.class);
        NotificationService notif = mock(NotificationService.class);
        GradeCalculator gradeCalc = mock(GradeCalculator.class);

        Student s = new Student();
        s.setStudentId("S004");
        s.setAcademicStatus("ACTIVE");

        Course c = new Course();
        c.setCourseCode("CS105");
        c.setCourseName("Jaringan Komputer");
        c.setCapacity(30);
        c.setEnrolledCount(30);

        when(studentRepo.findById("S004")).thenReturn(s);
        when(courseRepo.findByCourseCode("CS105")).thenReturn(c);

        EnrollmentService service = new EnrollmentService(studentRepo, courseRepo, notif, gradeCalc);
        assertThrows(CourseFullException.class, () ->
                service.enrollCourse("S004", "CS105"));
    }

    @Test
    void testPrerequisiteNotMetThrowsException() {
        StudentRepository studentRepo = mock(StudentRepository.class);
        CourseRepository courseRepo = mock(CourseRepository.class);
        NotificationService notif = mock(NotificationService.class);
        GradeCalculator gradeCalc = mock(GradeCalculator.class);

        Student s = new Student();
        s.setStudentId("S005");
        s.setAcademicStatus("ACTIVE");

        Course c = new Course();
        c.setCourseCode("CS201");
        c.setCourseName("Algoritma");
        c.setCapacity(40);
        c.setEnrolledCount(10);

        when(studentRepo.findById("S005")).thenReturn(s);
        when(courseRepo.findByCourseCode("CS201")).thenReturn(c);
        when(courseRepo.isPrerequisiteMet("S005", "CS201")).thenReturn(false);

        EnrollmentService service = new EnrollmentService(studentRepo, courseRepo, notif, gradeCalc);
        assertThrows(PrerequisiteNotMetException.class, () ->
                service.enrollCourse("S005", "CS201"));
    }
}
