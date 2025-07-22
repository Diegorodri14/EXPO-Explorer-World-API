package Explorer.World.Explorer.World.Controller.Viajes;

import Explorer.World.Explorer.World.Models.DTO.ViajesDTO.ViajesDTO;
import Explorer.World.Explorer.World.Service.Viajes.ViajesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/EXPO_Explorer_World_Sistema_web")
public class ViajesController {

    //Inyectamos el Service sobre Controller
    @Autowired
    ViajesService service;


    //------------------- CONSULTAR DATOS

    @GetMapping("/Viajes.html")
    public List<ViajesDTO> obtenerDatos(){
        return service.obtenerViajes();
    }

}
