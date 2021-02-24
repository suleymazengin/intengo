package com.intengo.intengo.service.impl;

import com.intengo.intengo.domain.User;
import com.intengo.intengo.repository.UserRepository;
import com.intengo.intengo.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    private final BCryptPasswordEncoder bcryptEncoder;


    private final UserRepository userRepository;

    public UserServiceImpl(BCryptPasswordEncoder bcryptEncoder, UserRepository userRepository) {
        this.bcryptEncoder = bcryptEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {

            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;

    }
}
