package agency.amazon.test.security.filter;


import agency.amazon.test.security.exception.authorization.AuthorizationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletResponse;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

class ExceptionHandlingFilterTest {
    private ExceptionHandlingFilter exceptionHandlingFilter;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain filterChain;

    @BeforeEach
    public void setup() {
        exceptionHandlingFilter = new ExceptionHandlingFilter(mock(ObjectMapper.class));
        request = mock(HttpServletRequest.class);
        response = new MockHttpServletResponse();
        filterChain = mock(FilterChain.class);
    }

    @Test
    public void testDoFilterInternal_WhenNoException_ShouldPassThrough() throws ServletException, IOException {
        exceptionHandlingFilter.doFilterInternal(request, response, filterChain);
        assertThat(response.getStatus()).isEqualTo(HttpServletResponse.SC_OK);
    }

    @Test
    public void testDoFilterInternal_WhenSecurityAuthException_ShouldHandleException() throws ServletException, IOException {
        var exception = new AuthorizationException("some error", new RuntimeException());
        doThrow(exception).when(filterChain).doFilter(request, response);
        exceptionHandlingFilter.doFilterInternal(request, response, filterChain);
        assertThat(response.getStatus()).isEqualTo(HttpServletResponse.SC_UNAUTHORIZED);
        assertThat(response.getHeader(HttpHeaders.WWW_AUTHENTICATE)).isEqualTo(exception.getMessage());
    }

    @Test
    public void testDoFilterInternal_WhenOtherException_ShouldNotHandleException() throws ServletException, IOException {
        RuntimeException exception = new RuntimeException();
        doThrow(exception).when(filterChain).doFilter(request, response);
        assertThatThrownBy(() -> exceptionHandlingFilter.doFilterInternal(request, response, filterChain))
                .isInstanceOf(RuntimeException.class)
                .isEqualTo(exception);
        assertThat(response.getStatus()).isEqualTo(HttpServletResponse.SC_OK);
    }
}