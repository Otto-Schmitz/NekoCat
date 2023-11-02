package br.com.nekocat.security.domain.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserSecurity implements UserDetails {

    private static final String PREFIX_ROLE = "ROLE_";
    private Long id;
    private String email;
    private String password;
    private boolean active;
    private List<SimpleGrantedAuthority> roles;

    public UserSecurity(Users user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.roles = convertRoles(user);
    }

    private List<SimpleGrantedAuthority> convertRoles(Users user) {
        return user.getRoles().stream()
                .map(permissao -> new SimpleGrantedAuthority(PREFIX_ROLE + permissao.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.active;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
