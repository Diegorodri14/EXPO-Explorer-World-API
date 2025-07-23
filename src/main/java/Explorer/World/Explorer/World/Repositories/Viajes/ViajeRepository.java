package Explorer.World.Explorer.World.Repositories.Viajes;

import Explorer.World.Explorer.World.Entities.Viajes.ViajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeRepository extends JpaRepository<ViajeEntity, Long> {
}
