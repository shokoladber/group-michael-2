package org.launchcode.caninecoach.services;

import com.google.api.services.classroom.Classroom;
import com.google.api.services.classroom.ClassroomScopes;
import com.google.api.services.classroom.model.Course;
import com.google.api.services.classroom.model.ListCoursesResponse;
import com.google.api.services.classroom.model.Student;
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
            return new Classroom.Builder(GoogleNetHttpTransport.newTrustedTransport(), new GsonFactory(), new HttpCredentialsAdapter(credentials))
                    .setApplicationName("Google Classroom API Example")
                    .build();
        } catch (GeneralSecurityException e) {
            log.error("Failed to initialize Google Classroom service", e);
            throw new IOException("Failed to initialize Google Classroom service due to security issues", e);
        }
    }

    public List<Course> listCourses() throws IOException, GeneralSecurityException {
        Classroom service = classroomService();
        ListCoursesResponse response = service.courses().list()
                .execute();

        List<Course> courses = response.getCourses();
        if (courses == null || courses.isEmpty()) {
            log.info("No courses found.");
            return Collections.emptyList();
        } else {
            courses.forEach(course -> log.info("Course ID: {}, Name: {}", course.getId(), course.getName()));
            return courses;
        }
    }
    public Student addStudentToCourse(String courseId, String studentEmail) throws IOException, GeneralSecurityException {
        Student student = new Student().setUserId(studentEmail);
        Classroom service = classroomService();

        return service.courses().students().create(courseId, student).execute();
    }

}

