package by.triumgroup.recourse.configuration.security;

import by.triumgroup.recourse.entity.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

public class UserAuthDetails extends User implements UserDetails, Serializable {

    public UserAuthDetails(User user) {
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(
                getRole().name()
        );
    }

    public boolean isAdmin() {
        return getRole() == Role.ADMIN;
    }

    public boolean isTeacher() {
        return getRole() == Role.TEACHER;
    }

    public boolean isStudent() {
        return getRole() == Role.STUDENT;
    }

    public boolean hasAnyRole(Role... roles) {
        for (Role role : roles) {
            if (role == getRole()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getPassword() {
        return getPasswordHash();
    }

    @Override
    public String getUsername() {
        return getEmail();
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
        return getRole() != Role.DISABLED;
    }
}
