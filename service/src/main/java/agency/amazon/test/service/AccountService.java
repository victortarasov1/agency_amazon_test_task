package agency.amazon.test.service;

import agency.amazon.test.dto.AccountDto;
import agency.amazon.test.dto.AccountWithDetailsDto;

public interface AccountService {

    void addAccount(AccountWithDetailsDto dto);

    AccountDto getAccount(String email);

}
