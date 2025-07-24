package Explorer.World.Explorer.World.Controller.Empleados;


import Explorer.World.Explorer.World.Exception.ExcepcionEmpleadoNoRegistrado;
import Explorer.World.Explorer.World.Exception.ExceptionDatosDuplicados;
import Explorer.World.Explorer.World.Models.DTO.EmpleadosDTO.EmpleadoDTO;
import Explorer.World.Explorer.World.Service.Empleados.EmpleadoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiEmpleados")
public class EmpleadoController {

    @Autowired
    EmpleadoService service;

    // ------------ CONSULTAR DATOS
    @GetMapping("/consultarEmpleado")
    public ResponseEntity<List<EmpleadoDTO>> obtenerEmpleado() {
        try {
            List<EmpleadoDTO> empleados = service.ObtenerEmpleado();

            if (empleados.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(empleados);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    // ------------ INSERCIÓN DE DATOS
    @PostMapping("/registrarEmpleado")
    public ResponseEntity<?> registrarEmpleado(
            @Valid @RequestBody EmpleadoDTO json, BindingResult bindingResult, HttpServletRequest request) {

        // Validar errores de binding
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "Validation Error",
                    "errors", errores
            ));
        }

        try {
            EmpleadoDTO respuesta = service.NuevoEmpleado(json);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "Success",
                    "message", "Empleado registrado exitosamente",
                    "data", respuesta
            ));
        } catch (ExceptionDatosDuplicados e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "status", "Conflict",
                    "message", "Datos duplicados",
                    "field", e.getCampoDuplicado(),
                    "details", e.getMessage()
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "Bad Request",
                    "message", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "status", "Error",
                            "message", "Error interno al registrar empleado",
                            "details", e.getMessage()
                    ));
        }
    }

    // ----------------- EDITAR DATOS

    @PutMapping("/actualizarEmpleado/{id}")
    public ResponseEntity<?> ModificarEmpleado(
            @PathVariable Long id,
            @Valid @RequestBody EmpleadoDTO data,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }

        try {
            EmpleadoDTO dto = service.ModificarEmpleado(id, data);
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Registro actualizado",
                    "data", dto
            ));
        } catch (ExcepcionEmpleadoNoRegistrado e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "Error", "NOT FOUND",
                            "Mensaje", e.getMessage(),
                            "timestamp", Instant.now().toString()
                    ));
        } catch (ExceptionDatosDuplicados e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    Map.of("Error", "Datos Duplicados",
                            "Campo", e.getCampoDuplicado(),
                            "message", e.getMessage())
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "Error",
                    "message", "Error al actualizar el usuario",
                    "details", e.getMessage()
            ));
        }
    }

    @DeleteMapping("/eliminarRegistro/{id}")
    public ResponseEntity<?> EliminarEmpleado(@PathVariable Long id) {
        try {
            if (!service.EliminarEmpleado(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
                                "Error", "NOT FOUND",
                                "Mensaje", "El usuario no fue encontrado",
                                "timestamp", Instant.now().toString()
                        ));
            }
            return ResponseEntity.ok().body(Map.of(
                    "status", "Success",
                    "message", "Usuario eliminado exitosamente"
            ));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "Error",
                    "message", "Error al eliminar el usuario",
                    "details", e.getMessage()
            ));
        }
    }

}
