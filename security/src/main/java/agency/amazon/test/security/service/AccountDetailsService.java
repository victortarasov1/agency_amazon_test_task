package agency.amazon.test.security.service;

import agency.amazon.test.repository.AccountRepository;
import agency.amazon.test.security.exception.authentication.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountDetailsService implements UserDetailsService {
    private final AccountRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findByEmail(username).orElseThrow(() -> new UserNotFoundException(username));
    }
}
