package alessiovulpinari.NexusFantasiaBE.repositories.races;

import alessiovulpinari.NexusFantasiaBE.entities.races.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RaceRepository extends JpaRepository<Race, UUID> {
    Optional<Race> findByName(String name);
}
