package Explorer.World.Explorer.World.Service.Clientes;

import Explorer.World.Explorer.World.Entities.Clientes.ClientesEntity;
import Explorer.World.Explorer.World.Exception.ExcepcionClienteNoEncontrado;
import Explorer.World.Explorer.World.Exception.ExcepcionClienteNoRegistrado;
import Explorer.World.Explorer.World.Models.DTO.ClientesDTO.ClientesDTO;
import Explorer.World.Explorer.World.Repositories.Clientes.ClientesRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientesService {

    @Autowired
    ClientesRepository repo;

    public List<ClientesDTO> obtenerClientes(){
        List <ClientesEntity> lista = repo.findAll();
        return lista.stream()
                .map(this::ConvertirADTO)
                .collect(Collectors.toList());

    }

    private ClientesDTO ConvertirADTO(ClientesEntity clientes){

        ClientesDTO dto = new ClientesDTO();

        dto.setIdCliente(clientes.getId());
        dto.setNombreCliente(clientes.getNombre());
        dto.setApellidoCliente(clientes.getApellido());
        dto.setEmailCliente(clientes.getCorreo());
        dto.setTelefono(clientes.getTelefono());
        dto.setDireccion(clientes.getDireccion());
        dto.setDUI(clientes.getDui());

        return dto;
    }

    public ClientesDTO InsertarDatos(ClientesDTO data) {
        if (data == null || data.getEmailCliente() == null || data.getEmailCliente().isEmpty()){
            throw new IllegalArgumentException("El email del cliente no pueden ser nulos");
        }
        try {
            ClientesEntity entity =  ConvertirAEntity(data);
            ClientesEntity usuarioGuardado = repo.save(entity);
            return ConvertirADTO(usuarioGuardado);
        }
        catch (Exception e) {
            log.error("Error al regitrar el usuario: " + e.getMessage());
            throw new ExcepcionClienteNoRegistrado("Error al registrar el usuario.");
        }
    }

    private ClientesEntity ConvertirAEntity(ClientesDTO data) {
        ClientesEntity entity = new ClientesEntity();
        entity.setNombre(data.getNombreCliente());
        entity.setApellido(data.getApellidoCliente());
        entity.setCorreo(data.getEmailCliente());
        entity.setTelefono(data.getTelefono());
        entity.setDireccion(data.getDireccion());
        entity.setDui(data.getDUI());

        return entity;
    }

    public ClientesDTO actualizarUsuario(Long id, @Valid ClientesDTO json) {
        //1. Verificar la existencia del usuario

        ClientesEntity ClienteExis = repo.findById(id).orElseThrow(() -> new ExcepcionClienteNoEncontrado("Usuario no encontrado"));

        //2. Convertir los datos de DTO a Entity

        ClienteExis.setNombre(json.getNombreCliente());
        ClienteExis.setApellido(json.getApellidoCliente());
        ClienteExis.setCorreo(json.getEmailCliente());
        ClienteExis.setTelefono(json.getTelefono());
        ClienteExis.setDireccion(json.getDireccion());
        ClienteExis.setDui(json.getDUI());

        //3. Guardar los cambios(nuevos valores)

        ClientesEntity ClienteActualizado = repo.save(ClienteExis);

        //4. Convertir los datos de Entity a DTO

        return ConvertirADTO(ClienteActualizado);
    }

    public boolean removerUsuario (Long id){
        try{
            //1.Validar la existencia del usuario
            ClientesEntity ClienteExis = repo.findById(id).orElse(null);
            //2.Eliminar al usuario
            if (ClienteExis != null){
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
