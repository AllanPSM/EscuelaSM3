package com.formacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.formacion.entities.User;
import com.formacion.service.ApiService;
import com.formacion.service.UserService;

import lombok.extern.java.Log;

@Controller
@Log
public class Controlador {

    @Autowired
    private UserService userService;

    @Autowired
    private ApiService apiService; // Inyectamos ApiService
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Ruta para la página principal. Redirige automáticamente al login.
     * @return vista "login"
     */
    @GetMapping("/")
    public String redirigirALogin() {
        return "redirect:/login";  // Redirige a la vista de login
    }

    /**
     * Muestra el formulario de registro.
     * @param model
     * @return vista "index" (registro.jsp o lo que sea que estés usando)
     */
    @GetMapping("/index")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("texto", "Formulario de Usuario");
        return "index";  // Asegúrate de que el archivo sea index.jsp o el que estés utilizando
    }

    /**
     * Procesa el formulario y crea el usuario.
     * @param email
     * @param password
     * @param model
     * @return vista con el mensaje y el usuario creado
     */
    @PostMapping("/procesarFormulario")
    public String procesarFormulario(String email, String password, Model model) {
        log.info("Procesando el formulario");

        String mensaje;
        User usuario = new User();
        usuario.setEmail(email);
        usuario.setPassword(passwordEncoder.encode(password));
        
        // Verificar si el usuario ya existe
        User existingUser = userService.findUserByEmail(email);

        if (existingUser == null) {
            // Si no existe, crear el usuario
            userService.saveUser(usuario);
            mensaje = "El usuario ha sido creado con éxito.";
        } else {
            // Si ya existe, mostrar un mensaje
            mensaje = "El usuario ya está registrado.";
        }

        model.addAttribute("mensaje", mensaje);
        model.addAttribute("user", usuario);

        return "index"; // Regresar a la misma vista con el mensaje
    }

    /**
     * Ruta para mostrar la página de login (login.jsp).
     * @param model
     * @return vista "login"
     */
    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        model.addAttribute("texto", "Inicia sesión.");
        return "login";  // Redirige a la vista de login
    }

    /**
     * Procesa el formulario de login.
     * @param email
     * @param password
     * @param model
     * @return vista de inicio si las credenciales son correctas
     */
    @PostMapping("/iniciarSesion")
    public String iniciarSesion(String email, String password, Model model) {
        log.info("Iniciando sesión con el email: " + email);

        // Verificar si las credenciales son correctas
        User user = userService.findUserByEmail(email);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // Si el usuario existe y la contraseña es correcta
            model.addAttribute("texto", "Bienvenido " + user.getEmail());
            return "redirect:/inicio";  // Redirigir a la página de inicio
        } else {
            // Si el usuario no existe o la contraseña es incorrecta
            model.addAttribute("mensaje", "Credenciales incorrectas. Intenta nuevamente.");
            return "login";  // Regresar al formulario de login
        }
    }

    /**
     * Página de inicio después de iniciar sesión con éxito.
     * @param model
     * @return vista "inicio"
     */
    @GetMapping("/inicio")
    public String mostrarInicio(Model model) {
        // Obtenemos los datos de la API utilizando el ApiService
        String apiData = apiService.getApiData();
        
        // Añadimos los datos al modelo
        model.addAttribute("texto", "Bienvenido al inicio");
        model.addAttribute("apiData", apiData);  // Aquí añadimos los datos de la API al modelo
        
        return "inicio";  // Nombre de la vista JSP (inicio.jsp)
    }

    /**
     * Ruta para cambiar entre las vistas de login y registro
     * @param source
     * @return vista login o index
     */
    @GetMapping("/loginRegistro")
    public String procesarFormularioLogin(String source) {
        return source.equalsIgnoreCase("registro") ? "login" : "index";
    }
}
