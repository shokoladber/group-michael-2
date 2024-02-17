package org.launchcode.caninecoach.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper; import org.springframework.security.core.AuthenticationException; import org.springframework.security.web.AuthenticationEntryPoint; import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest; import jakarta.servlet.http.HttpServletResponse; import java.io.IOException; import java.io.Serial; import java.io.Serializable; import java.util.HashMap; import java.util.Map;

@Component public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Serial
    private static final long serialVersionUID = -7858869558953243875L;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");

        Map<String, Object> data = new HashMap<>();
        data.put("timestamp", System.currentTimeMillis());
        data.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        data.put("message", "Access Denied");
        data.put("path", request.getRequestURI());

        // Write the response as a JSON string
        ObjectMapper mapper = new ObjectMapper();
        response.getOutputStream().println(mapper.writeValueAsString(data));
    }
}
