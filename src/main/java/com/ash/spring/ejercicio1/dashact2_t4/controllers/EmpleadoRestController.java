package com.ash.spring.ejercicio1.dashact2_t4.controllers;

import com.ash.spring.ejercicio1.dashact2_t4.models.dto.EmpleadoDTO;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/empleados")
public class EmpleadoRestController {

    @PostMapping("/crear")
    public ResponseEntity<Map<String, Object>> crearEmpleadoRest(@RequestBody EmpleadoDTO dto) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("status", "Exito");
        respuesta.put("mensaje", "Empleado recibido");
        respuesta.put("data", dto);

        return ResponseEntity.ok(respuesta);
    }
}
