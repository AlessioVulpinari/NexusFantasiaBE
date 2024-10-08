package alessiovulpinari.NexusFantasiaBE.repositories.races;

import alessiovulpinari.NexusFantasiaBE.entities.races.Race;
import alessiovulpinari.NexusFantasiaBE.entities.races.RacialTraits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RacialTraitRepository extends JpaRepository<RacialTraits, UUID> {
    Optional<RacialTraits> findByName(String name);
}
