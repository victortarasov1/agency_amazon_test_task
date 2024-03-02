package agency.amazon.test.security.filter;

import agency.amazon.test.security.exception.authentication.BadPasswordOrEmailException;
import agency.amazon.test.security.exception.authentication.UserNotFoundException;
import agency.amazon.test.security.service.TokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@Order(1)
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider provider;
    private final ObjectMapper mapper;
    private final AuthenticationManager manager;

    static final String EMAIL_PARAM = "email";
    static final String PASSWORD_PARAM = "password";
    static final String LOGIN_URI = "/login";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals(LOGIN_URI)) {
            var tokens = authenticate(request);
            response.setContentType(APPLICATION_JSON_VALUE);
            mapper.writeValue(response.getOutputStream(), tokens);
        }
        else filterChain.doFilter(request, response);
    }

    private Map<String, String> authenticate(HttpServletRequest request) {
        var email = request.getParameter(EMAIL_PARAM);
        var password = request.getParameter(PASSWORD_PARAM);
        var authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        var details = (UserDetails) getUserDetails(authenticationToken);
        return provider.generateTokens(details);

    }

    private UserDetails getUserDetails(UsernamePasswordAuthenticationToken token) {
        try {
            return (UserDetails) manager.authenticate(token).getPrincipal();
        } catch (InternalAuthenticationServiceException | BadCredentialsException | UserNotFoundException ex) {
            throw new BadPasswordOrEmailException(token.getName());
        }
    }
}
