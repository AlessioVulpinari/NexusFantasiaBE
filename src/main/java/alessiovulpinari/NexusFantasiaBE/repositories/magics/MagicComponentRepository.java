package alessiovulpinari.NexusFantasiaBE.repositories.magics;

import alessiovulpinari.NexusFantasiaBE.entities.magics.MagicComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MagicComponentRepository extends JpaRepository<MagicComponent, UUID> {
    Optional<MagicComponent> findByName(String name);
}
