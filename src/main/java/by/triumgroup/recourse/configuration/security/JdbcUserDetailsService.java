package by.triumgroup.recourse.configuration.security;

import by.triumgroup.recourse.entity.User;
import by.triumgroup.recourse.service.UserService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Component
public class JdbcUserDetailsService implements UserDetailsService {


    private final UserService userService;

    @Inject
    public JdbcUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            username = URLDecoder.decode(username, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UsernameNotFoundException("Wrong encoding");
        }
        User user = userService.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found in database.");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPasswordHash(),
                true,
                true,
                true,
                true,
                AuthorityUtils.createAuthorityList("USER", "write"));
    }

}
