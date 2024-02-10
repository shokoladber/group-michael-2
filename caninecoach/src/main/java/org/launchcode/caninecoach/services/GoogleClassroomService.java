package org.launchcode.caninecoach.services;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.classroom.Classroom;
import com.google.api.services.classroom.ClassroomScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Objects;

@Service
public class GoogleClassroomService {

    private static final Logger log = LoggerFactory.getLogger(GoogleClassroomService.class);

    @Value("${google.service-account.filename}")
    private String credentialsFilePath;

    @RequestScope
    public Classroom classroomService() throws IOException {

        GoogleCredentials credentials = GoogleCredentials.fromStream(Objects.requireNonNull(getClass().getResourceAsStream("/" + credentialsFilePath)))
                .createScoped(ClassroomScopes.all());

        try {
            return new Classroom.Builder(GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance(), new HttpCredentialsAdapter(credentials))
                    .setApplicationName("Google Classroom API Example")
                    .build();
        } catch (GeneralSecurityException e) {

            throw new IOException("Failed to initialize Google Classroom service due to security issues", e);
        }
    }
}
