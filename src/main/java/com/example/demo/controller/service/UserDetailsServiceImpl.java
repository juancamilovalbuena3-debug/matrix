package com.example.hr.service;

import com.example.hr.model.User;
import com.example.hr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email); // buscar por email
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),          // usamos email como username
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")) // rol por defecto
        );
    }
}
