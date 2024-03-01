package agency.amazon.test.dto;

import agency.amazon.test.model.Account;

public record AccountDto(String email, String name, String surname) {
    public AccountDto(Account account) {
        this(account.getEmail(), account.getName(), account.getSurname());
    }
}
