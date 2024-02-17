package org.launchcode.caninecoach.security;

import org.launchcode.caninecoach.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {
    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean isVerified;
    private String name;

    public UserPrincipal(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities, boolean isVerified, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.isVerified = isVerified;
        this.name = name;
    }

    public static UserPrincipal create(User user) {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().name());
        Collection<GrantedAuthority> authorities = Collections.singletonList(authority);

        return new UserPrincipal(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                authorities,
                user.isVerified(),
                user.getName()
        );
    }

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
        return email; // Use email as the username for Spring Security
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // You can implement your own logic here
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // You can implement your own logic here
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // You can implement your own logic here
    }

    @Override
    public boolean isEnabled() {
        return isVerified; // Use the verification status to determine if the account is enabled
    }

    // Custom getters for id, email, name, and verification status
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public boolean isVerified() {
        return isVerified;
    }
}
