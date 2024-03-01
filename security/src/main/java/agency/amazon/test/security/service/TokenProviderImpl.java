package agency.amazon.test.security.service;

import agency.amazon.test.security.dto.ConfigHolder;
import agency.amazon.test.security.exception.authentication.UserNotFoundException;
import agency.amazon.test.security.exception.authorization.BadTokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
@Component
@RequiredArgsConstructor
public class TokenProviderImpl implements TokenProvider {
    private final Algorithm algorithm;
    private final JWTVerifier verifier;
    private final UserDetailsService service;
    private final ConfigHolder holder;

    static final String ACCESS_TOKEN = "access_token";
    static final String REFRESH_TOKEN = "refresh_token";
    @Override
    public Map<String, String> generateTokens(UserDetails details) {
        return Map.of(ACCESS_TOKEN, generateAccessToken(details),
                REFRESH_TOKEN, generateRefreshToken(details));
    }

    @Override
    public Map<String, String> regenerateTokensIfRefreshTokenIsValid(String refreshToken) {
        try {
            var decodedJWT = verifier.verify(refreshToken);
            var username = decodedJWT.getSubject();
            var user = service.loadUserByUsername(username);
            return Map.of(ACCESS_TOKEN, generateAccessToken(user));
        } catch (TokenExpiredException | JWTDecodeException | UserNotFoundException ex) {
            throw new BadTokenException(ex);
        }
    }

    private String generateRefreshToken(UserDetails user) {
        var instant = Instant.now();
        return JWT.create().withSubject(user.getUsername())
                .withExpiresAt(instant.plus(holder.getRefreshTokenTime(), ChronoUnit.MINUTES))
                .sign(algorithm);
    }

    private String generateAccessToken(UserDetails user) {
        var instant = Instant.now();
        return JWT.create().withSubject(user.getUsername())
                .withExpiresAt(instant.plus(holder.getAccessTokenTime(), ChronoUnit.MINUTES))
                .withClaim(TokenClaim.ROLES.getClaim(), user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).toList()).sign(algorithm);
    }
}
