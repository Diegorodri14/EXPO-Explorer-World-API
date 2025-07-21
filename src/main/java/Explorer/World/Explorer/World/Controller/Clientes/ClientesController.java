package Explorer.World.Explorer.World.Controller.Clientes;

import Explorer.World.Explorer.World.Models.DTO.ClientesDTO.ClientesDTO;
import Explorer.World.Explorer.World.Service.Clientes.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/EXPO_Explorer_World__Sistema_web")
public class ClientesController {

    //Inyectamos el Service sobre Controller
    @Autowired
    ClientesService service;


    //------------------- CONSULTAR DATOS

    @GetMapping("/Viajes.html")
    public List<ClientesDTO> obtenerDatos(){
        return service.obtenerClientes();
    }

}
