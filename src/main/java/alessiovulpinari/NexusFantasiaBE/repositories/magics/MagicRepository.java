package alessiovulpinari.NexusFantasiaBE.repositories.magics;

import alessiovulpinari.NexusFantasiaBE.entities.magics.Magic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MagicRepository extends JpaRepository<Magic, UUID> {
    Optional<Magic> findByName(String name);
}
