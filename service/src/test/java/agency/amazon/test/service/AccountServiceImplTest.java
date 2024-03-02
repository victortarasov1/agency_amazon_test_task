package agency.amazon.test.service;

import agency.amazon.test.dto.AccountDto;
import agency.amazon.test.dto.AccountWithDetailsDto;
import agency.amazon.test.exception.AccountAlreadyExistException;
import agency.amazon.test.exception.AccountNotFoundException;
import agency.amazon.test.model.Account;
import agency.amazon.test.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountServiceImplTest {
    private AccountRepository accountRepository;
    private PasswordEncoder encoder;
    private AccountService service;
    @BeforeEach
    public void setUp() {
        accountRepository = mock(AccountRepository.class);
        encoder = mock(PasswordEncoder.class);
        service = new AccountServiceImpl(accountRepository, encoder);
    }

    @Test
    public void addAccount() {
        var dto = new AccountWithDetailsDto("some email", "some name", "some surname", "some password");
        when(accountRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(encoder.encode(anyString())).thenReturn("some encoded string");
        service.addAccount(dto);
    }

    @Test
    public void addAccount_shouldThrowAccountAlreadyExistException() {
        var dto = new AccountWithDetailsDto("some email", "some name", "some surname", "some password");
        var account = new Account(dto.email(), dto.password(), dto.name(), dto.surname());
        when(accountRepository.findByEmail(anyString())).thenReturn(Optional.of(account));
        assertThatThrownBy(() -> service.addAccount(dto))
                .isInstanceOf(AccountAlreadyExistException.class);
    }

    @Test
    public void getAccount() {
        var email = "some email";
        var account = new Account("some email", "some password", "some name", "some surname");
        var dto = new AccountDto(account.getEmail(), account.getName(), account.getSurname());
        
        when(accountRepository.findByEmail(anyString())).thenReturn(Optional.of(account));
        var result = service.getAccount(email);
        assertEquals(result, dto);
    }

    @Test
    public void getAccount_shouldThrowAccountNotFoundException() {
        var email = "some email";
        when(accountRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.getAccount(email))
                .isInstanceOf(AccountNotFoundException.class);
    }


}