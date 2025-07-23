package Explorer.World.Explorer.World.Entities.Viajes;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name = "CLIENTES")
@Getter @Setter @ToString @EqualsAndHashCode
public class ViajeEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "sq_Clientes")
    @SequenceGenerator(name = "sq_Clientes", sequenceName = "sq_Clientes", allocationSize = 1)
    @Column(name = "IdCliente")
    private Long id;

    @Column(name = "NombreCliente")
    private String nombre;

    @Column(name = "ApellidoCliente")
    private String apellido;

    @Column(name = "EmailCliente")
    private String correo;

    @Column(name = "Telefono")
    private Long telefono;

    @Column(name = "Direccion")
    private String direccion;

    @Column(name = "DUI")
    private Long dui;
}
