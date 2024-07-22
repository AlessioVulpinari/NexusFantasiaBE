package alessiovulpinari.NexusFantasiaBE.repositories.sheet;

import alessiovulpinari.NexusFantasiaBE.entities.sheet.AbilityScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AbilityScoreRepository extends JpaRepository<AbilityScore, UUID> {
    Optional<AbilityScore> findByName(String name);
}
