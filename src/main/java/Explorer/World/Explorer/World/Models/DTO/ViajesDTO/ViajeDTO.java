package Explorer.World.Explorer.World.Models.DTO.ViajesDTO;


import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString @EqualsAndHashCode
public class ViajeDTO {


    private Long IDVIAJE;

    private Long IDCLIENTE;

    private Long IDEMPLEADO;

    private Long IDTRANSPORTE;

    @NotBlank
    private Number PRECIO;

    @NotBlank
    private String NOMBREVIAJE;

    @NotBlank
    private String DESTINO;


    private Date FECHA;

    private Boolean ESTADO;

    private Long IDHORARIO;

    private Long IDSERVICIO;

    private Date FECHASALIDA;

    private Date FECHAREGRESO;
}
