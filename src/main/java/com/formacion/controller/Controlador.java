package com.formacion.controller;


import com.formacion.entities.User;
import com.formacion.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.extern.java.Log;

import java.util.Date;

@Controller
@Log
public class Controlador {

    private final UserService userServicio;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public Controlador(UserService userServicio, PasswordEncoder passwordEncoder) {
        this.userServicio = userServicio;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/login";  // Redirige a la vista de login
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, 
                        @RequestParam String password, 
                        Model model) {
        log.info("Intentando loguear con email: " + email);

        if (userServicio.verifyUserCredentials(email, password)) {
            User user = userServicio.findUserByEmail(email);
            log.info("Cliente encontrado: " + user.getEmail());
            model.addAttribute("nombreCliente", user.getEmail());
            return "inicio";
        } else {
            log.warning("Falló la autenticación. Volviendo a login.jsp");
            model.addAttribute("error", "Email o contraseña incorrectos");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String cerrarSesion() {
        return "redirect:/";
    }

    @GetMapping("/index")
    public String mostrarRegistro() {
        return "index";
    }

    @PostMapping("/index")
    public String procesarRegistro(
                                   @RequestParam String email,
                                   @RequestParam String password,
                                   Model model) {
    	User user = new User();
       
    	user.setEmail(email);
    	user.setPassword(passwordEncoder.encode(password));

        try {
        	userServicio.saveUser(user);
            model.addAttribute("mensaje", "Usuario registrado con éxito: " + email);
            return "index";
        } catch (Exception e) {
            model.addAttribute("mensajeError", "Error al registrar el usuario. Intenta de nuevo.");
            return "index";
        }
    }
}
