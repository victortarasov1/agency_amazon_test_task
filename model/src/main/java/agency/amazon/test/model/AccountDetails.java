package agency.amazon.test.model;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
@EqualsAndHashCode(of = "email")
@Document
public class AccountDetails implements UserDetails {

    @Id
    private String email;
    private String password;

    private List<Role> roles = List.of(Role.ROLE_USER);
    private boolean isAccountExpired = false;
    private boolean isAccountLocked = false;
    private boolean isCredentialsExpired = false;
    private boolean isEnabled = true;


    public AccountDetails(String email, String password) {
        this.email = email;
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(Role::toString).map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isAccountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isAccountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isCredentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
