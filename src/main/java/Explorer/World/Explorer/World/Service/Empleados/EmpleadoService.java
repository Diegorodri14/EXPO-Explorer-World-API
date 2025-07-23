package Explorer.World.Explorer.World.Service.Empleados;

import Explorer.World.Explorer.World.Entities.Empleados.EmpleadoEntity;
import Explorer.World.Explorer.World.Exception.ExcepcionEmpleadoNoEncontrado;
import Explorer.World.Explorer.World.Exception.ExcepcionEmpleadoNoRegistrado;
import Explorer.World.Explorer.World.Models.DTO.EmpleadosDTO.EmpleadoDTO;
import Explorer.World.Explorer.World.Repositories.Empleados.EmpleadoRespository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRespository repo;

    public List<EmpleadoDTO> obtenerEmpleados() {
        List<EmpleadoEntity> lista = repo.findAll();
        System.out.println("Total empleados: "  + lista.size());
        return lista.stream()
                .map(this::ConvertirADTO)
                .collect(Collectors.toList());
    }


    /*
    *
    * CONVERTIR A DTO
    *
    * */

    private EmpleadoDTO ConvertirADTO(EmpleadoEntity entity){

        EmpleadoDTO dto = new EmpleadoDTO();

        dto.setIdEmpleado(entity.getId());
        dto.setIdRango(entity.getIdRango());
        dto.setNombreEmpleado(entity.getNombreEm());
        dto.setApellidoEmpleado(entity.getApellidoEm());
        dto.setEmailEmpleado(entity.getCorreoEm());
        dto.setFechaNacimiento(entity.getFechaNacimiento());
        dto.setTelefono(entity.getTelefono());
        dto.setDireccion(entity.getDireccion());
        dto.setSalario(entity.getSalario());


        return dto;
    }

    public EmpleadoDTO InsertarDatosEmpleados(EmpleadoDTO data){
        if (data == null || data.getEmailEmpleado() == null){
            throw new IllegalArgumentException("Correo no puede ser nulo");
        }
        try{
            EmpleadoEntity entity = ConvertirEntity(data);
            EmpleadoEntity EmpleadoGuardado = repo.save(entity);
            return ConvertirADTO(EmpleadoGuardado);
        }
        catch (Exception e){
            log.error("Error al registrar el empleado: " + e.getMessage());
            throw new ExcepcionEmpleadoNoRegistrado("Error al registrar el empleado");
        }

    }

    private EmpleadoEntity ConvertirEntity(EmpleadoDTO data){

        EmpleadoEntity entity = new EmpleadoEntity();

        entity.setNombreEm(data.getNombreEmpleado());
        entity.setApellidoEm(data.getApellidoEmpleado());
        entity.setCorreoEm(data.getEmailEmpleado());
        entity.setFechaNacimiento(data.getFechaNacimiento());
        entity.setTelefono(data.getTelefono());
        entity.setDireccion(data.getDireccion());
        entity.setSalario(data.getSalario());
        entity.setIdRango(data.getIdRango());

        return entity;
    }

    public EmpleadoDTO actualizarUsuario(Long id, @Valid EmpleadoDTO json) {
        //1. Verificar la existencia del usuario

        EmpleadoEntity existente = repo.findById(id).orElseThrow(() -> new ExcepcionEmpleadoNoEncontrado("Usuario no encontrado"));

        //2. Convertir los datos de DTO a Entity

        existente.setIdRango(json.getIdRango());
        existente.setNombreEm(json.getNombreEmpleado());
        existente.setApellidoEm(json.getApellidoEmpleado());
        existente.setCorreoEm(json.getEmailEmpleado());
        existente.setFechaNacimiento(json.getFechaNacimiento());
        existente.setTelefono(json.getTelefono());
        existente.setDireccion(json.getDireccion());
        existente.setSalario(json.getSalario());

        //3. Guardar los cambios(nuevos valores)

        EmpleadoEntity empleadoActualizado = repo.save(existente);

        //4. Convertir los datos de Entity a DTO

        return ConvertirADTO(empleadoActualizado);
    }

    public boolean removerEmpleado (Long id){
        try{
            //1.Validar la existencia del usuario
            EmpleadoEntity usuarioExistente = repo.findById(id).orElse(null);
            //2.Eliminar al usuario
            if (usuarioExistente != null){
                repo.deleteById(id);
                return true;
            }else {
                return false;
            }
        }catch (EmptyResultDataAccessException e){
            throw  new EmptyResultDataAccessException("No se encontro al empleado con el ID: " + id + "Para eliminar.", 1);
        }
    }
}
