package agency.amazon.test.security.filter;

import agency.amazon.test.model.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@Order(0)
@RequiredArgsConstructor
public class ExceptionHandlingFilter extends OncePerRequestFilter {
    private final ObjectMapper mapper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (SecurityException ex) {
            response.setHeader(HttpHeaders.WWW_AUTHENTICATE, ex.getMessage());
            response.setStatus(UNAUTHORIZED.value());
            var debugMessages = Optional.ofNullable(ex.getCause()).map(Throwable::getMessage).stream().toList();
            var error = new ApiError(ex.getMessage(), debugMessages);
            response.setContentType(APPLICATION_JSON_VALUE);
            mapper.writeValue(response.getOutputStream(), error);
        }
    }
}
