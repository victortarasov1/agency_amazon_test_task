package agency.amazon.test.dto;

import agency.amazon.test.model.Account;
import agency.amazon.test.model.AccountDetails;

public record AccountWithDetailsDto(String email, String name, String surname, String password) {
    public Account dtoToAccount() {
        var details = new AccountDetails(email, password);
        return new Account(email, name, surname, details);
    }
}
