package com.formacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.formacion.entities.User;
import com.formacion.repository.UserRepositorio;

@Service
public class UserService {
    private final UserRepositorio userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepositorio userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


  
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email); // Consulta a la base de datos
    }

    
    public void saveUser(User user) {
    	userRepository.save(user); // Guardar el usuario en la base de datos
    }
    
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    
 public boolean verifyUserCredentials(String email, String rawPassword) {
    	
        User user = userRepository.findByEmail(email);
          
        if (user != null) {
            // Usar la instancia de passwordEncoder para verificar la contraseña
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }

        return false;
    }
    
}
