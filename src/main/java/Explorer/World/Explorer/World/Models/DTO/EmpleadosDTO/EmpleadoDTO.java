package Explorer.World.Explorer.World.Models.DTO.EmpleadosDTO;

import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter @ToString @EqualsAndHashCode
public class EmpleadoDTO {

    private Long IdEmpleado;

    @NotBlank
    private String NombreEmpleado;

    @NotBlank
    private String ApellidoEmpleado;

    @Email (message = "Debe de ser un correo valido")
    private String EmailEmpleado;

    @DateTimeFormat (style = "DD/MM/YYYY")
    private Date FechaNacimiento;

    @NotBlank
    private Long Telefono;

    private String Direccion;

    @Size (min = 10, message = "El salario debe de ser menos de 10 digitos")
    private Number Salario;

    @NotNull (message = "El campo IdCargo tiene que tener un valor")
    @Positive(message = "El valor id rango debe ser positivo")
    private Integer IdRango;
}
