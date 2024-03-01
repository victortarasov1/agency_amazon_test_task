package agency.amazon.test.security.filter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AuthorizationType {
    BEARER("Bearer ");
    private final String prefix;
}
