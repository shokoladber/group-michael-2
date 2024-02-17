package org.launchcode.caninecoach.security;

import org.launchcode.caninecoach.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
        );

        return new UserPrincipal(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    // Methods from UserDetails interface
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email; // Assuming the email is used as the username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implement your logic or return true if not handling expired accounts
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implement your logic or return true if not handling locked accounts
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implement your logic or return true if not handling expired credentials
    }

    @Override
    public boolean isEnabled() {
        return true; // Implement your logic or return true if the user is enabled
    }
}