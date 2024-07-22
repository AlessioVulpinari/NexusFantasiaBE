package alessiovulpinari.NexusFantasiaBE.repositories.races;

import alessiovulpinari.NexusFantasiaBE.entities.races.IncrementedScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IncrementedScoreRepository extends JpaRepository<IncrementedScore, UUID> {
}
