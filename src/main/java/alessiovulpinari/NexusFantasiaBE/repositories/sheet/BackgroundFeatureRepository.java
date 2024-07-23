package alessiovulpinari.NexusFantasiaBE.repositories.sheet;

import alessiovulpinari.NexusFantasiaBE.entities.sheet.BackgroundFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BackgroundFeatureRepository extends JpaRepository<BackgroundFeature, UUID> {
    Optional<BackgroundFeature> findByName(String name);
}
