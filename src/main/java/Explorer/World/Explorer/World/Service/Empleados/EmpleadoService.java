package Explorer.World.Explorer.World.Service.Empleados;

import Explorer.World.Explorer.World.Entities.Empleados.EmpleadoEntity;
import Explorer.World.Explorer.World.Exception.ExcepcionEmpleadoNoRegistrado;
import Explorer.World.Explorer.World.Models.DTO.EmpleadosDTO.EmpleadoDTO;
import Explorer.World.Explorer.World.Repositories.Empleados.EmpleadoRespository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRespository repo;

    public List<EmpleadoDTO> ObtenerEmpleado() {
        try {
            List<EmpleadoEntity> lista = repo.findAll();
            log.info("Empleados encontrados: {}", lista.size());

            return lista.stream()
                    .map(this::ConvertirADTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error al obtener empleados: {}", e.getMessage());
            return new ArrayList<>();
        }
    }


    public EmpleadoDTO NuevoEmpleado(@Valid EmpleadoDTO data) {
        if (data == null || data.getEmailEmpleado() == null || data.getEmailEmpleado().isEmpty()) {
            throw new IllegalArgumentException("El correo del empleado no pueden ser nulos");
        }
        try {
            EmpleadoEntity entity = ConvertirAEntity(data);
            EmpleadoEntity EmpleadoGuardado = repo.save(entity);
            return ConvertirADTO(EmpleadoGuardado);
        } catch (Exception e) {
            log.error("Error al registrar el empleado: " + e.getMessage());
            throw new ExcepcionEmpleadoNoRegistrado("Error al registrar el empleado.");
        }
    }

    private EmpleadoEntity ConvertirAEntity(EmpleadoDTO data) {
        EmpleadoEntity entity = new EmpleadoEntity();
        entity.setId(data.getIdEmpleado());
        entity.setId(data.getIdEmpleado());
        entity.setIdRango(data.getIdRango());
        entity.setNombreEm(data.getNombreEmpleado());
        entity.setApellidoEm(data.getApellidoEmpleado());
        entity.setCorreoEm(data.getEmailEmpleado());
        entity.setFechaNacimiento(data.getFechaNacimiento());
        entity.setTelefono(data.getTelefono());
        entity.setDireccion(data.getDireccion());
        entity.setSalario(data.getSalario());
        return entity;
    }

    private EmpleadoDTO ConvertirADTO(EmpleadoEntity entity) {
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

    public EmpleadoDTO ModificarEmpleado(Long id, @Valid EmpleadoDTO json) {
        EmpleadoEntity existente = repo.findById(id).orElseThrow(() -> new ExcepcionEmpleadoNoRegistrado("Usuario no encontrado"));

        existente.setIdRango(json.getIdRango());
        existente.setNombreEm(json.getNombreEmpleado());
        existente.setApellidoEm(json.getApellidoEmpleado());
        existente.setCorreoEm(json.getEmailEmpleado());
        existente.setFechaNacimiento(json.getFechaNacimiento());
        existente.setTelefono(json.getTelefono());
        existente.setDireccion(json.getDireccion());
        existente.setSalario(json.getSalario());

        EmpleadoEntity EmpleadoActualizado = repo.save(existente);
        return ConvertirADTO(EmpleadoActualizado);
    }

    public boolean EliminarEmpleado(Long id) {
        try {
            if (repo.existsById(id)) {
                repo.deleteById(id);
                return true;
            }
            return false;
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException("No se encontró al usuario con el ID: " + id, 1);
        }
    }
}
