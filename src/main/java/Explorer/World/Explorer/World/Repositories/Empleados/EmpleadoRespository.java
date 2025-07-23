package Explorer.World.Explorer.World.Repositories.Empleados;

import Explorer.World.Explorer.World.Entities.Empleados.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRespository extends JpaRepository<EmpleadoEntity, Long>{
}
