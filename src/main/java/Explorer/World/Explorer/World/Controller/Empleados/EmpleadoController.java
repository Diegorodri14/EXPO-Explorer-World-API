package Explorer.World.Explorer.World.Controller.Empleados;

import Explorer.World.Explorer.World.Models.DTO.EmpleadosDTO.EmpleadoDTO;
import Explorer.World.Explorer.World.Service.Empleados.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apiEmpleados")
public class EmpleadoController {

    @Autowired
    EmpleadoService service;

    //------------------- CONSULTAR DATOS

    @GetMapping("/consultarDatos")
    public List<EmpleadoDTO> obtenerDatos(){
        return service.obtenerEmpleados();
    }

}
