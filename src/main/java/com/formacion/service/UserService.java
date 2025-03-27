package com.formacion.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.formacion.entities.User;
import com.formacion.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
