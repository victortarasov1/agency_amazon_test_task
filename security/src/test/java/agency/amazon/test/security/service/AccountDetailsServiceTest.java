package agency.amazon.test.security.service;

import agency.amazon.test.model.Account;
import agency.amazon.test.repository.AccountRepository;
import agency.amazon.test.security.exception.authentication.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AccountDetailsServiceTest {
    private UserDetailsService service;
    private AccountRepository repository;

    @BeforeEach
    public void setUp() {
        repository = mock(AccountRepository.class);
        service = new AccountDetailsService(repository);
    }

    @Test
    void testLoadUserByUsername() {
        var details = new Account("email@gmail.com", "root", "name", "surname");
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