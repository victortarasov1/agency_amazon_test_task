package agency.amazon.test.security.service;

import agency.amazon.test.model.Role;
import agency.amazon.test.security.exception.authorization.AuthorizationException;
import agency.amazon.test.security.exception.authorization.BadTokenException;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.auth0.jwt.interfaces.Claim;

class AuthorizationServiceImplTest {

    private JWTVerifier verifier;
    private DecodedJWT decodedJWT;

    private Claim claim;
    private AuthorizationService service;

    @BeforeEach
    public void setUp() {
        verifier = mock(JWTVerifier.class);
        decodedJWT = mock(DecodedJWT.class);
        claim = mock(Claim.class);
        service = new AuthorizationServiceImpl(verifier);
    }


    @Test
    public void testAuthorizeIfTokenIsValid() {
        var roles = List.of(Role.ROLE_USER.toString());
        var authorities = List.of(new SimpleGrantedAuthority(Role.ROLE_USER.toString()));
        var username = "some email";
        var token = "some token";
        var authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
        when(verifier.verify(anyString())).thenReturn(decodedJWT);
        when(decodedJWT.getSubject()).thenReturn(username);
        when(decodedJWT.getClaim(TokenClaim.ROLES.getClaim())).thenReturn(claim);
        when(claim.asList(String.class)).thenReturn(roles);
        service.authorizeIfTokenIsValid(token);
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isEqualTo(authenticationToken);
    }

    @Test
    public void testAuthorizeIfTokenValid_ShouldThrowAuthorizationException_WhenTokenIsInvalid() {
        var token = "some token";
        when(verifier.verify(token)).thenThrow(SignatureVerificationException.class);
        assertThatThrownBy(() -> service.authorizeIfTokenIsValid(token))
                .isInstanceOf(BadTokenException.class)
                .hasCauseInstanceOf(SignatureVerificationException.class);
    }

    @Test
    public void testAuthorizeIfTokenValid_ShouldThrowAuthorizationException_WhenTokenIsMalformed() {
        var token = "some token";
        when(verifier.verify(token)).thenThrow(JWTDecodeException.class);
        assertThatThrownBy(() -> service.authorizeIfTokenIsValid(token))
                .isInstanceOf(AuthorizationException.class)
                .hasCauseInstanceOf(JWTDecodeException.class);
    }

    @Test
    public void testAuthorizeIfTokenValid_ShouldThrowAuthorizationException_WhenTokenIsExpired() {
        var token = "some token";
        when(verifier.verify(token)).thenThrow(TokenExpiredException.class);
        assertThatThrownBy(() -> service.authorizeIfTokenIsValid(token))
                .isInstanceOf(AuthorizationException.class)
                .hasCauseInstanceOf(TokenExpiredException.class);
    }

}