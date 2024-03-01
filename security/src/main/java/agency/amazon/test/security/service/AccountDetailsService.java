package agency.amazon.test.security.service;

import agency.amazon.test.repository.AccountDetailsRepository;
import agency.amazon.test.security.exception.authentication.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountDetailsService implements UserDetailsService {
    private final AccountDetailsRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findById(username).orElseThrow(() -> new UserNotFoundException(username));
    }
}
