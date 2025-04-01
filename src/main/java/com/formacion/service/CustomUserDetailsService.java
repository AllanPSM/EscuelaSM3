package com.formacion.service;

import com.formacion.entities.User;
import com.formacion.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscar el usuario por email
        User user = userRepository.findByEmail(email);
        
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el email: " + email);
        }
        
        // Crear y devolver un UserDetails con el usuario y el rol
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())  // Usamos el email como nombre de usuario
                .password(user.getPassword()) // La contraseña hasheada
                .roles(user.getIsAdmin() ? "ADMIN" : "USER")  // Asumiendo que es admin si isAdmin es true
                .build();
    }
}
