package com.unibeck.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Customer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String displayName;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Customer withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Customer withUsername(String username) {
        this.username = username;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Customer withPassword(String password) {
        this.password = password;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
