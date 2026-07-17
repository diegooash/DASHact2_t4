package com.ash.spring.ejercicio1.dashact2_t4.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EjemploRestController {

    @GetMapping("/api/identificacion")
    public Map<String, String> identificacion() {
        Map<String, String> info = new HashMap<>();
        info.put("proyecto", "DASHact2_t4");
        info.put("autor", "Diego Alonso Sanchez Hernandez");
        info.put("iniciales", "DASH");
        info.put("entorno", "Desarrollo local");
        return info;
    }

    @GetMapping("/api/materias")
    public List<Map<String, Object>> materias() {
        List<Map<String, Object>> lista = new ArrayList<>();

        Map<String, Object> materiaUno = new HashMap<>();
        materiaUno.put("id", 101);
        materiaUno.put("nombre", "Conmutacion y Enrutamiento");
        materiaUno.put("horario", "7:00 a 10:00");
        lista.add(materiaUno);

        Map<String, Object> materiaDos = new HashMap<>();
        materiaDos.put("id", 102);
        materiaDos.put("nombre", "Programacion Web");
        materiaDos.put("horario", "10:00 a 13:00");
        lista.add(materiaDos);

        return lista;
    }

    @GetMapping("/detalles_info2")
    public Map<String, Object> infoJson() {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("titulo", "Servidor en linea (JSON)");
        respuesta.put("servidor", "DASH");
        respuesta.put("ip", "127.0.0.1");
        return respuesta;
    }
}
