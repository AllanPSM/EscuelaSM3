package com.formacion.service;

import com.formacion.config.SecurityConfig;
import com.formacion.controller.Controlador;

import lombok.extern.java.Log;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Log
public class ApiService {

    private final SecurityConfig securityConfig; // Mantenemos la dependencia a SecurityConfig

    private final WebClient webClient;

    public ApiService(WebClient webClient, SecurityConfig securityConfig) {
        this.webClient = webClient;
        this.securityConfig = securityConfig;
    }

    public String getApiData() {
        String url = "https://dummyjson.com/users/1"; // URL de la API
        String response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Bloquea para obtener el resultado (no reactivo)
        
        if (response.isEmpty()) {
            log.info("No muestra valores de API");
        } else {
            log.info(response);// Imprime la respuesta de la API para depuración
        }

        return response;
    }
}
