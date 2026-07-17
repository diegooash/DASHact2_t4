package com.ash.spring.ejercicio1.dashact2_t4.controllers;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EjemploController {

    @GetMapping("/detalles_info")
    public String info(Map<String, Object> modelo) {
        modelo.put("titulo", "Servidor en linea");
        modelo.put("servidor", "DASH");
        modelo.put("ip", "127.0.0.1");
        return "detalles_info";
    }
}
