package alessiovulpinari.NexusFantasiaBE.repositories.races;

import alessiovulpinari.NexusFantasiaBE.entities.races.Subrace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubraceRepository extends JpaRepository<Subrace, UUID> {
    Optional<Subrace> findByName(String name);
}
