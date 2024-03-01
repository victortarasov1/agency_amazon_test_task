package agency.amazon.test.security.filter;

import agency.amazon.test.security.service.AuthorizationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static agency.amazon.test.security.filter.AuthorizationType.BEARER;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@Order(3)
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {
    private final AuthorizationService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var header = request.getHeader(AUTHORIZATION);
        if (containsBearerToken(header)) service.authorizeIfTokenIsValid(header.substring(BEARER.getPrefix().length()));
        filterChain.doFilter(request, response);
    }

    private boolean containsBearerToken(String header) {
        return header != null && header.startsWith(BEARER.getPrefix());
    }
}
