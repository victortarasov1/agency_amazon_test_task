package agency.amazon.test.security.service;

import agency.amazon.test.security.exception.authorization.BadTokenException;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {
    private final JWTVerifier verifier;

    @Override
    public void authorizeIfTokenIsValid(String accessToken) {
        try {
            var decodedJWT = verifier.verify(accessToken);
            var username = decodedJWT.getSubject();
            var roles = decodedJWT.getClaim(TokenClaim.ROLES.getClaim()).asList(String.class);
            var authorities = roles.stream().map(SimpleGrantedAuthority::new).toList();
            var authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (TokenExpiredException | JWTDecodeException | SignatureVerificationException ex) {
            throw new BadTokenException(ex);
        }
    }
}
