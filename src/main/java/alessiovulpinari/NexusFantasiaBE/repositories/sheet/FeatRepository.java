package alessiovulpinari.NexusFantasiaBE.repositories.sheet;

import alessiovulpinari.NexusFantasiaBE.entities.sheet.Feat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FeatRepository extends JpaRepository<Feat, UUID> {
    Optional<Feat> findByName(String name);
}
