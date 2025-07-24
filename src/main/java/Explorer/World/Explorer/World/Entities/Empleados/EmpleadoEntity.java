package Explorer.World.Explorer.World.Entities.Empleados;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name = "Empleados")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class EmpleadoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_Empleados")
    @SequenceGenerator(name = "sq_Empleados", sequenceName = "sq_Empleados", allocationSize = 1, initialValue = 1)
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
    private String fechaNacimiento;


    @Column(name = "TELEFONO")
    private String telefono;


    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "SALARIO")
    private Double salario;

}
