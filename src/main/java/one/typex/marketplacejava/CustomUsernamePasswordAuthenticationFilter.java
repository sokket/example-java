package one.typex.marketplacejava;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private String login;
    private String password;

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return password;
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return login;
    }

    private boolean isValidReq(HttpServletRequest request) {
        String contentType = request.getHeader("Content-Type");
        return Objects.equals(contentType, "application/json");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (isValidReq(request)) {
            try {
                var str = request.getReader().lines().collect(Collectors.joining());
                var mapper = Jackson2ObjectMapperBuilder.json().build();
                var loginRequest = mapper.readValue(str, LoginRequest.class);
                login = loginRequest.login;
                password = loginRequest.password;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return super.attemptAuthentication(request, response);
    }
}
