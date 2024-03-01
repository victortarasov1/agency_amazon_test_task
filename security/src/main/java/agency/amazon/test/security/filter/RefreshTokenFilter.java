package agency.amazon.test.security.filter;

import agency.amazon.test.security.service.TokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Component
@Order(2)
@RequiredArgsConstructor
public class RefreshTokenFilter extends OncePerRequestFilter {
    private final TokenProvider provider;
    private final ObjectMapper mapper;
    private static final String REFRESH_URI = "/refresh";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var header = request.getHeader(AUTHORIZATION);
        if(isRefreshRequest(header, request)) {
            var token = provider.regenerateTokensIfRefreshTokenIsValid(header.substring(BEARER.getPrefix().length()));
            response.setContentType(APPLICATION_JSON_VALUE);
            mapper.writeValue(response.getOutputStream(), token);
        }
        else filterChain.doFilter(request, response);
    }

    private boolean isRefreshRequest(String header, HttpServletRequest request) {
        return header != null && header.startsWith(BEARER.getPrefix()) && request.getRequestURI().equals(REFRESH_URI);
    }
}
