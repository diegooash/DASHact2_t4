package com.ash.spring.ejercicio1.dashact2_t4.controllers;

import com.ash.spring.ejercicio1.dashact2_t4.models.Empleados;
import com.ash.spring.ejercicio1.dashact2_t4.models.dto.EmpleadoDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mvc/empleados")
public class EmpleadoController {

    @Value("${config.autor}")
    private String autorProyecto;

    @Value("${config.proyecto}")
    private String nombreProyecto;

    @Value("${config.codigo}")
    private String codigoProyecto;

    @Value("${config.usuario}")
    private String usuarioProyecto;

    private final List<Empleados> listaEmpleados = new ArrayList<>(List.of(
            new Empleados("Diego", "Ash", "Calle 123", "Estudiante", 21, 951474444, 1),
            new Empleados("Juan", "Perez", "Avenida 456", "Ingeniero", 30, 987654321, 2),
            new Empleados("Maria", "Gomez", "Boulevard 789", "Abogada", 28, 123456789, 3)
    ));

    @GetMapping("/listar")
    public String listarEmpleados(Model model) {
        cargarEncabezado(model, "Listado de Empleados");
        model.addAttribute("empleados", listaEmpleados);
        return "listado_empleados";
    }

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("titulo", "Registrar Nuevo Empleado (DTO)");
        model.addAttribute("empleadoDTO", new EmpleadoDTO());
        return "formulario_empleado";
    }

    @PostMapping("/guardar")
    public String guardarEmpleado(@ModelAttribute("empleadoDTO") EmpleadoDTO dto, Model model) {
        int nuevoId = listaEmpleados.size() + 1;
        Empleados nuevo = new Empleados(
                dto.getNombre(),
                dto.getApellido(),
                "Sin Direccion",
                dto.getPuesto(),
                dto.getEdad() != null ? dto.getEdad() : 0,
                0,
                nuevoId
        );
        listaEmpleados.add(nuevo);

        model.addAttribute("titulo", "Empleado Guardado con Exito");
        model.addAttribute("empleado", dto);
        return "resultado_formulario";
    }

    @GetMapping("/filtrar")
    public String filtrarPorPuesto(
            @RequestParam(name = "puesto", required = false, defaultValue = "Todos") String puesto,
            Model model) {
        List<Empleados> filtrados;
        if ("Todos".equalsIgnoreCase(puesto)) {
            filtrados = listaEmpleados;
        } else {
            filtrados = listaEmpleados.stream()
                    .filter(empleado -> empleado.getPuesto().equalsIgnoreCase(puesto))
                    .collect(Collectors.toList());
        }

        cargarEncabezado(model, "Filtro por Puesto: " + puesto);
        model.addAttribute("empleados", filtrados);
        return "listado_empleados";
    }

    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable int id, Model model) {
        Empleados encontrado = listaEmpleados.stream()
                .filter(empleado -> empleado.getId() == id)
                .findFirst()
                .orElse(null);

        model.addAttribute("titulo", "Detalle del Empleado ID: " + id);
        model.addAttribute("empleado", encontrado);
        return "detalle_id";
    }

    private void cargarEncabezado(Model model, String titulo) {
        model.addAttribute("titulo", titulo);
        model.addAttribute("autor", autorProyecto);
        model.addAttribute("proyecto", nombreProyecto);
        model.addAttribute("codigo", codigoProyecto);
        model.addAttribute("usuario", usuarioProyecto);
    }
}
