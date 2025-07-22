package Explorer.World.Explorer.World.Service.Viajes;

import Explorer.World.Explorer.World.Entities.Viajes.ViajesEntity;
import Explorer.World.Explorer.World.Exception.ExcepcionViajeNoEncontrado;
import Explorer.World.Explorer.World.Exception.ExcepcionViajeNoRegistrado;
import Explorer.World.Explorer.World.Models.DTO.ViajesDTO.ViajesDTO;
import Explorer.World.Explorer.World.Repositories.Viajes.ViajesRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ViajesService {

    @Autowired
    ViajesRepository repo;

    public List<ViajesDTO> obtenerViajes(){
        List <ViajesEntity> lista = repo.findAll();
        return lista.stream()
                .map(this::ConvertirADTO)
                .collect(Collectors.toList());

    }

    private ViajesDTO ConvertirADTO(ViajesEntity viajes){

        ViajesDTO dto = new ViajesDTO();

        dto.setIdCliente(viajes.getId());
        dto.setNombreCliente(viajes.getNombre());
        dto.setApellidoCliente(viajes.getApellido());
        dto.setEmailCliente(viajes.getCorreo());
        dto.setTelefono(viajes.getTelefono());
        dto.setDireccion(viajes.getDireccion());
        dto.setDUI(viajes.getDui());

        return dto;
    }

    public ViajesDTO InsertarDatos(ViajesDTO data) {
        if (data == null || data.getEmailCliente() == null || data.getEmailCliente().isEmpty()){
            throw new IllegalArgumentException("El email del cliente no pueden ser nulos");
        }
        try {
            ViajesEntity entity =  ConvertirAEntity(data);
            ViajesEntity viajeGuardado = repo.save(entity);
            return ConvertirADTO(viajeGuardado);
        }
        catch (Exception e) {
            log.error("Error al regitrar el usuario: " + e.getMessage());
            throw new ExcepcionViajeNoRegistrado("Error al registrar el usuario.");
        }
    }

    private ViajesEntity ConvertirAEntity(ViajesDTO data) {
        ViajesEntity entity = new ViajesEntity();
        entity.setNombre(data.getNombreCliente());
        entity.setApellido(data.getApellidoCliente());
        entity.setCorreo(data.getEmailCliente());
        entity.setTelefono(data.getTelefono());
        entity.setDireccion(data.getDireccion());
        entity.setDui(data.getDUI());

        return entity;
    }

    public ViajesDTO actualizarUsuario(Long id, @Valid ViajesDTO json) {
        //1. Verificar la existencia del usuario

        ViajesEntity ClienteExis = repo.findById(id).orElseThrow(() -> new ExcepcionViajeNoEncontrado("Usuario no encontrado"));

        //2. Convertir los datos de DTO a Entity

        ClienteExis.setNombre(json.getNombreCliente());
        ClienteExis.setApellido(json.getApellidoCliente());
        ClienteExis.setCorreo(json.getEmailCliente());
        ClienteExis.setTelefono(json.getTelefono());
        ClienteExis.setDireccion(json.getDireccion());
        ClienteExis.setDui(json.getDUI());

        //3. Guardar los cambios(nuevos valores)

        ViajesEntity ClienteActualizado = repo.save(ClienteExis);

        //4. Convertir los datos de Entity a DTO

        return ConvertirADTO(ClienteActualizado);
    }

    public boolean removerUsuario (Long id){
        try{
            //1.Validar la existencia del usuario
            ViajesEntity ViajeExist = repo.findById(id).orElse(null);
            //2.Eliminar al usuario
            if (ViajeExist != null){
                repo.deleteById(id);
                return true;
            }else {
                return false;
            }
        }catch (EmptyResultDataAccessException e){
            throw  new EmptyResultDataAccessException("No se encontro al usuario con el ID: " + id + "Para eliminar.", 1);
        }
    }

}
