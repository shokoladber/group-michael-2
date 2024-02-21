package org.launchcode.caninecoach.services;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    private final Configuration freemarkerConfig;

    @Value("${sendgrid.api-key}")
    private String sendGridApiKey;

    @Autowired
    public EmailService(Configuration freemarkerConfig) {
        this.freemarkerConfig = freemarkerConfig;
    }

    public void sendTemplateVerificationEmail(String to, String subject, String verificationLink) throws IOException {
        log.info("Preparing to send verification email to {}", to);

        try {
            // Get the FreeMarker template
            Template template = freemarkerConfig.getTemplate("verificationEmailTemplate.ftl");

            // Prepare the model for the template
            Map<String, Object> model = new HashMap<>();
            model.put("verificationUrl", verificationLink);

            // Process the template into a String
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

            // Construct the email using SendGrid's classes
            Email fromEmail = new Email("caninecoachapp@gmail.com");
            Email toEmail = new Email(to);
            Content emailContent = new Content("text/html", content);
            Mail mail = new Mail(fromEmail, subject, toEmail, emailContent);

            // Send the email using SendGrid
            SendGrid sg = new SendGrid(sendGridApiKey);
            Request request = new Request();

            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            log.info("Verification email sent successfully to {}", to);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());

        } catch (IOException | TemplateException e) {
            log.error("Exception occurred when sending email to {}: {}", to, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
