package agency.amazon.test.security.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TokenClaim {
    ROLES("roles");
    private final String claim;
}
