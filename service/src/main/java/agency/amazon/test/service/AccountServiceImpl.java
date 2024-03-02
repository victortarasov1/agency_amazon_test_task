package agency.amazon.test.service;

import agency.amazon.test.dto.AccountDto;
import agency.amazon.test.dto.AccountWithDetailsDto;
import agency.amazon.test.exception.AccountAlreadyExistException;
import agency.amazon.test.exception.AccountNotFoundException;
import agency.amazon.test.model.Account;
import agency.amazon.test.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder encoder;
    @Override
    public void addAccount(AccountWithDetailsDto dto) {
        if(accountRepository.findByEmail(dto.email()).isEmpty()) {
            accountRepository.save(new Account(dto.email(),encoder.encode(dto.password()), dto.name(), dto.surname()));
        } else throw new AccountAlreadyExistException(dto.email());
    }

    @Override
    @Cacheable(value = "account", key = "#email")
    public AccountDto getAccount(String email) {
        return accountRepository.findByEmail(email).map(AccountDto::new)
                .orElseThrow(() -> new AccountNotFoundException(email));
    }

    @Override
    @CacheEvict(value = "account", key = "#email")
    public void remove(String email) {
        accountRepository.removeByEmail(email);
    }

    @Override
    @CacheEvict(value = "account", key = "#oldEmail")
    public void update(String oldEmail, AccountWithDetailsDto dto) {
        var account = accountRepository.findByEmail(oldEmail)
                .orElseThrow(() -> new AccountNotFoundException(oldEmail));
        if(oldEmail.equals(dto.email()) || accountRepository.findByEmail(dto.email()).isEmpty()) updateAccount(dto, account);
        else throw new AccountAlreadyExistException(dto.email());
    }

    private void updateAccount(AccountWithDetailsDto dto, Account account) {
        accountRepository.removeByEmail(account.getEmail());
        account.setName(dto.name());
        account.setEmail(dto.email());
        account.setPassword(encoder.encode(dto.password()));
        account.setSurname(dto.surname());
        accountRepository.save(account);
    }
}
