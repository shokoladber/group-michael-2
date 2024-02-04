package org.launchcode.caninecoach.services;


import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.classroom.Classroom;
import com.google.api.services.classroom.model.ListCoursesResponse;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;


import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;


@Service
public class GoogleClassroomService {


    private final ResourceLoader resourceLoader;
    private Classroom classroom;


    public GoogleClassroomService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    @PostConstruct
    public void init() throws IOException, GeneralSecurityException {
        InputStream serviceAccountStream = resourceLoader.getResource("classpath:google-service-account.json").getInputStream();



        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccountStream)
                .createScoped(Collections.singletonList("https://www.googleapis.com/auth/classroom.courses.readonly"));



        classroom = new Classroom.Builder(GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance(), new HttpCredentialsAdapter(credentials))
                .setApplicationName("Canine Coach")
                .build();
    }



    public ListCoursesResponse listCourses() throws IOException {
        Classroom.Courses.List request = classroom.courses().list();
        return request.execute();
    }
}
