package agency.amazon.test.security.service;

import agency.amazon.test.model.AccountDetails;
import agency.amazon.test.repository.AccountDetailsRepository;
import agency.amazon.test.security.exception.authentication.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AccountDetailsServiceTest {
    private UserDetailsService service;
    private AccountDetailsRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(AccountDetailsRepository.class);
        service = new AccountDetailsService(repository);
    }

    @Test
    void testLoadUserByUsername() {
        var details = new AccountDetails("email@gmail.com", "root");
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(details));
        var result = service.loadUserByUsername(details.getUsername());
        assertNotNull(result);
        assertEquals(details, result);
    }

    @Test
    void testLoadUserByUsername_shouldThrowUserNotFoundException() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.loadUserByUsername("some mail"))
                .isInstanceOf(UserNotFoundException.class);
    }

}