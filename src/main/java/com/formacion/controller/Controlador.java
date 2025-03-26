package com.formacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.formacion.entities.User;
import com.formacion.service.UserService;

import lombok.extern.java.Log;

/**
 * Controller para manejar las vistas y los formularios
 * 
 */
@Controller
@Log
public class Controlador {

    @Autowired
    private UserService userService;

    /**
     * Solicitud GET para la ruta raíz (localhost:8080/), que muestra la vista de inicio.
     * @param model
     * @return vista "index"
     */
    @GetMapping("/")
    public String inicio(Model model) {
        log.info("Arranca la app");
        model.addAttribute("texto", "Texto de ejemplo");
        return "index";
    }

    /**
     * Recibe el dato del formulario y lo reenvía de vuelta a la vista "index".
     * @param email
     * @param password
     * @param model
     * @return vista "index"
     */
    @PostMapping("/procesarFormulario")
    public String procesarFormulario( String email, String password, Model model) {
        log.info("Procesamos el formulario");
        log.info("Usuario: " + email);
        log.info("Contraseña: " + password);

        String mensaje = null;
        User usuario = new User();
        usuario.setEmail(email);
        usuario.setPassword(password);
        System.out.println("Email recibido: " + email);  // Para verificar

        // Usamos el servicio para buscar por email
        User existingUser = userService.findUserByEmail(email);

        if (existingUser == null) {
            // Si no existe, lo guardamos
            userService.saveUser(usuario);
            mensaje = "El usuario " + usuario.getEmail() + " ha sido creado.";
        } else {
            // Si ya existe, mostramos un mensaje
            mensaje = "El usuario " + usuario.getEmail() + " ya está registrado.";
        }

        // Añadir los datos al modelo para mostrarlos en la vista
        model.addAttribute("usuario", usuario);
        model.addAttribute("mensaje", mensaje);

        return "index"; // Redirigir a la vista "index"
    }
    // Ruta para mostrar la página de login (login.jsp)
    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("texto", "Inicia sesión.");
        return "login"; // Asegura que coincida con el nombre del archivo JSP
    }

    
    @PostMapping("/iniciarSesion")
    public String iniciarSesion( String email, String password, Model model) {
        log.info("Intento de inicio de sesión con email: " + email);

        User usuario = userService.findUserByEmail(email);

        if (usuario == null) {
            log.warning("Usuario no encontrado.");
            model.addAttribute("error", "Usuario no encontrado.");
            return "login";  // Regresa a login.jsp
        }

        log.info("Inicio de sesión exitoso.");
        return "redirect:/inicio";  // Redirige a la página principal
        
    }


    }

    


