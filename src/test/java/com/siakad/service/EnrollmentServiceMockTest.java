package com.siakad.service;

import com.siakad.model.Course;
import com.siakad.model.Enrollment;
import com.siakad.model.Student;
import com.siakad.repository.CourseRepository;
import com.siakad.repository.StudentRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EnrollmentServiceMockTest {

    @Test
    void testEnrollCourseSuccess() {
        // Mock dependencies
        StudentRepository studentRepo = mock(StudentRepository.class);
        CourseRepository courseRepo = mock(CourseRepository.class);
        NotificationService notificationService = mock(NotificationService.class);
        GradeCalculator gradeCalculator = mock(GradeCalculator.class);

        // Setup mock Student (pakai setter supaya tidak tergantung constructor)
        Student student = new Student();
        student.setStudentId("S001");
        student.setName("Titi");
        student.setEmail("titi@gmail.com");
        student.setGpa(3.8);
        student.setAcademicStatus("ACTIVE");

        // Setup mock Course (pakai setter sesuai Course.java)
        Course course = new Course();
        course.setCourseCode("CS101");
        course.setCourseName("Pemrograman");
        course.setCredits(3);
        course.setCapacity(30);
        course.setEnrolledCount(25);
        course.setLecturer("Dr. Rudi");

        // Define mock behavior
        when(studentRepo.findById("S001")).thenReturn(student);
        when(courseRepo.findByCourseCode("CS101")).thenReturn(course);
        when(courseRepo.isPrerequisiteMet("S001", "CS101")).thenReturn(true);

        EnrollmentService service = new EnrollmentService(studentRepo, courseRepo, notificationService, gradeCalculator);

        // Execute
        Enrollment result = service.enrollCourse("S001", "CS101");

        // Verify result and interactions
        assertNotNull(result);
        assertEquals("S001", result.getStudentId());
        assertEquals("CS101", result.getCourseCode());
        assertEquals("APPROVED", result.getStatus());

        // Verify notification sent to correct email
        verify(notificationService, times(1))
                .sendEmail(eq("titi@gmail.com"), anyString(), contains("Pemrograman"));

        // Verify course repository update was called
        verify(courseRepo, times(1)).update(course);
    }
}
