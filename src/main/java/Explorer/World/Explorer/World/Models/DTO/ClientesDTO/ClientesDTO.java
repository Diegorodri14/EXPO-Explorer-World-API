package Explorer.World.Explorer.World.Models.DTO.ClientesDTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @EqualsAndHashCode
public class ClientesDTO {


    private Long IdCliente;

    @NotBlank
    private String NombreCliente;

    @NotBlank
    private String ApellidoCliente;

    @Email (message = "Debe de ser un correo valido")
    private String EmailCliente;

    @Positive (message = "El numero de telefono tiene que ser positivo")
    private Long Telefono;


    private String Direccion;

    @NotBlank
    private Long DUI;
}
