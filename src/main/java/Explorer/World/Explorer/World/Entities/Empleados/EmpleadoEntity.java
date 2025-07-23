package Explorer.World.Explorer.World.Entities.Empleados;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table (name = "EMPLEADOS")
@Getter @Setter @ToString @EqualsAndHashCode
public class EmpleadoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_Empleados")
    @SequenceGenerator(name = "sq_Empleados", sequenceName = "sq_Empleados", allocationSize = 1)
    @Column (name = "IDEMPLEADO")
    private Long id;


    @Column(name = "IDRANGO")
    private Integer idRango;


    @Column(name = "NOMBREEMPLEADO")
    private String NombreEm;


    @Column(name = "APELLIDOEMPLEADO")
    private String ApellidoEm;


    @Column(name = "EMAILEMPLEADO")
    private String CorreoEm;


    @Column(name = "FECHANACIMIENTO")
    private Date fechaNacimiento;


    @Column(name = "TELEFONO")
    private Long telefono;


    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "SALARIO")
    private Number salario;

}
