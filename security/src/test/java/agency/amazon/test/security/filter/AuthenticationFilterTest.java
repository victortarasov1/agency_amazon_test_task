package agency.amazon.test.security.filter;

import agency.amazon.test.model.AccountDetails;
import agency.amazon.test.security.service.TokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.HashMap;

import static agency.amazon.test.security.filter.AuthenticationFilter.*;
import static org.mockito.Mockito.*;

class AuthenticationFilterTest {
    private TokenProvider provider;
    private ObjectMapper mapper;
    private AuthenticationManager manager;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain filterChain;
    private AuthenticationFilter filter;


    @BeforeEach
    public void setUp() {
        provider = mock(TokenProvider.class);
        mapper = mock(ObjectMapper.class);
        request = mock(HttpServletRequest.class);
        response = new MockHttpServletResponse();
        manager = mock(AuthenticationManager.class);
        filterChain = mock(FilterChain.class);
        filter = new AuthenticationFilter(provider, mapper, manager);
    }

    @Test
    public void doFilterInternal_shouldProcessFilterChainWithoutAuthenticating() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("/some uri");
        filter.doFilterInternal(request, response, filterChain);
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    public void doFilterInternal() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn(LOGIN_URI);
        when(request.getParameter(EMAIL_PARAM)).thenReturn("email");
        when(request.getParameter(PASSWORD_PARAM)).thenReturn("password");
        var details = new AccountDetails("email", "password");
        var authentication = mock(Authentication.class);
        when(manager.authenticate(any())).thenReturn(authentication);
        when(provider.generateTokens(eq(details))).thenReturn(new HashMap<>());
        filter.doFilterInternal(request, response, filterChain);

    }

}