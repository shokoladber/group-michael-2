package org.launchcode.caninecoach.services;

import com.google.api.services.classroom.Classroom;
import com.google.api.services.classroom.ClassroomScopes;
import com.google.api.services.classroom.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.auth.http.HttpCredentialsAdapter;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class GoogleClassroomService {

    private static final Logger log = LoggerFactory.getLogger(GoogleClassroomService.class);

    @Value("${google.service-account.filename}")
    private String credentialsFilePath;

    @RequestScope
    public Classroom classroomService() throws IOException, GeneralSecurityException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(Objects.requireNonNull(getClass().getResourceAsStream("/" + credentialsFilePath)))
                .createScoped(ClassroomScopes.all());

        try {
            return new Classroom.Builder(GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance(), new HttpCredentialsAdapter(credentials))
                    .setApplicationName("Canine Coach")
                    .build();
        } catch (GeneralSecurityException e) {
            log.error("Failed to initialize Google Classroom service", e);
            throw new IOException("Failed to initialize Google Classroom service due to security issues", e);
        }
    }

    // Trainer can create course
    public Course createCourse(Course course) throws IOException, GeneralSecurityException {
        Classroom service = classroomService();
        return service.courses().create(course).execute();
    }

    // Trainer can edit/update listed course
    public Course updateCourse(String courseId, Course course) throws IOException, GeneralSecurityException {
        Classroom service = classroomService();
        return service.courses().patch(courseId, course).execute();
    }

    // Trainer can delete course created
    public void deleteCourse(String courseId) throws IOException, GeneralSecurityException {
        Classroom service = classroomService();
        service.courses().delete(courseId).execute();
    }

    // Guardian can view list of courses
    public List<Course> listCourses() throws IOException, GeneralSecurityException {
        Classroom service = classroomService();
        ListCoursesResponse response = service.courses().list().execute();

        List<Course> courses = response.getCourses();
        if (courses == null || courses.isEmpty()) {
            log.info("No courses found.");
            return Collections.emptyList();
        } else {
            courses.forEach(course -> log.info("Course ID: {}, Name: {}", course.getId(), course.getName()));
            return courses;
        }
    }

    // Enrolls a pet guardian into the class they select
    public Student enrollStudentInCourse(String courseId, String studentEmail) throws IOException, GeneralSecurityException {
        Classroom service = classroomService();
        Student student = new Student().setUserId(studentEmail);
        return service.courses().students().create(courseId, student).execute();
    }

    // Trainer can view class roster list
    public List<Student> getCourseRoster(String courseId) throws IOException, GeneralSecurityException {
        Classroom service = classroomService();
        List<Student> students = service.courses().students().list(courseId).execute().getStudents();
        if (students == null) {
            log.info("No students found for course ID: {}", courseId);
            return Collections.emptyList();
        }
        return students;
    }

    // Trainer can make announcement for course
    public Announcement createAnnouncement(String courseId, Announcement announcement) throws IOException, GeneralSecurityException {
        Classroom service = classroomService();
        return service.courses().announcements().create(courseId, announcement).execute();
    }

    // Trainer can remove announcement for course
    public void deleteAnnouncement(String courseId, String announcementId) throws IOException, GeneralSecurityException {
        Classroom service = classroomService();
        service.courses().announcements().delete(courseId, announcementId).execute();
    }

    // Trainer can edit announcement for course
    public Announcement updateAnnouncement(String courseId, String announcementId, Announcement announcement) throws IOException, GeneralSecurityException {
        Classroom service = classroomService();
        return service.courses().announcements().patch(courseId, announcementId, announcement).execute();
    }

    // Guardian can view announcement by trainer for class
    public List<Announcement> listAnnouncements(String courseId) throws IOException, GeneralSecurityException {
        Classroom service = classroomService();
        ListAnnouncementsResponse response = service.courses().announcements().list(courseId).execute();
        return response.getAnnouncements();
    }

}

