package agency.amazon.test.service;

import agency.amazon.test.dto.AccountDto;
import agency.amazon.test.dto.AccountWithDetailsDto;
import agency.amazon.test.exception.AccountAlreadyExistException;
import agency.amazon.test.exception.AccountNotFoundException;
import agency.amazon.test.model.Account;
import agency.amazon.test.model.AccountDetails;
import agency.amazon.test.repository.AccountDetailsRepository;
import agency.amazon.test.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountDetailsRepository accountDetailsRepository;
    private final PasswordEncoder encoder;
    @Override
    public void addAccount(AccountWithDetailsDto dto) {
        if(accountRepository.findByEmail(dto.email()).isEmpty()) {
            var details = new AccountDetails(dto.email(), encoder.encode(dto.password()));
            accountRepository.save(new Account(dto.email(), dto.name(), dto.surname(), details));
            accountDetailsRepository.save(details);
        } else throw new AccountAlreadyExistException(dto.email());
    }

    @Override
    public AccountDto getAccount(String email) {
        return accountRepository.findById(email).map(AccountDto::new)
                .orElseThrow(() -> new AccountNotFoundException(email));
    }
}
