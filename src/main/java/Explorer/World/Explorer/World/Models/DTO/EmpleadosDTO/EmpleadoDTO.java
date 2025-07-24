package Explorer.World.Explorer.World.Models.DTO.EmpleadosDTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({
        "idEmpleado",
        "idRango",
        "nombreEmpleado",
        "apellidoEmpleado",
        "emailEmpleado",
        "fechaNacimiento",
        "telefono",
        "direccion",
        "salario"
})

@Getter @Setter @ToString @EqualsAndHashCode
public class EmpleadoDTO {

    private Long IdEmpleado;


    @NotNull (message = "El campo IdRango tiene que tener un valor")
    @Positive(message = "El valor id rango debe ser positivo")
    private Integer IdRango;

    @NotBlank(message = "El nombre del empleado no puede estar vacío")
    @Size(max = 35, message = "El estado no puede exceder los 20 caracteres")
    private String NombreEmpleado;

    @NotBlank(message = "El apellidos del empleado no puede estar vacío")
    @Size(max = 35, message = "El estado no puede exceder los 20 caracteres")
    private String ApellidoEmpleado;

    @NotBlank(message = "El correo del empleado no puede estar vacío")
    @Email(message = "Debe de ser un correo valido")
    private String EmailEmpleado;

    @NotBlank(message = "La fecha de nacimiento del empleado no puede estar vacío")
    private String FechaNacimiento;

    @NotBlank(message = "El telefono del empleado no puede estar vacío")
    private String Telefono;

    private String Direccion;

    private Double Salario;

}

