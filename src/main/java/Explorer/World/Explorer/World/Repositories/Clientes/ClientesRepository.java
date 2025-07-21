package Explorer.World.Explorer.World.Repositories.Clientes;

import Explorer.World.Explorer.World.Entities.Clientes.ClientesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends JpaRepository<ClientesEntity, Long> {
}
