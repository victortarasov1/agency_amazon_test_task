package agency.amazon.test.service;

import agency.amazon.test.dto.AccountDto;
import agency.amazon.test.dto.AccountWithDetailsDto;
import agency.amazon.test.exception.AccountAlreadyExistException;
import agency.amazon.test.exception.AccountNotFoundException;
import agency.amazon.test.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;
    @Override
    public void addAccount(AccountWithDetailsDto dto) {
        if(repository.findById(dto.email()).isEmpty()) repository.save(dto.dtoToAccount());
        else throw new AccountAlreadyExistException(dto.email());
    }

    @Override
    public AccountDto getAccount(String email) {
        return repository.findById(email).map(AccountDto::new)
                .orElseThrow(() -> new AccountNotFoundException(email));
    }
}
