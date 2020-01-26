package com.hikarikun92.springsecurity.security;

import com.hikarikun92.springsecurity.user.User;
import com.hikarikun92.springsecurity.user.UserCredentials;
import com.hikarikun92.springsecurity.user.UserCredentialsRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.hikarikun92.springsecurity.util.ApplicationUtils.convertSet;

@Primary
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserCredentialsRepository repository;

    public UserDetailsServiceImpl(UserCredentialsRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserCredentials credentials = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username \"" + username + "\" not found"));

        final User user = credentials.getUser();
        final Set<SimpleGrantedAuthority> authorities = convertSet(user.getRoles(), SimpleGrantedAuthority::new);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), credentials.getPassword(), authorities);
    }
}
