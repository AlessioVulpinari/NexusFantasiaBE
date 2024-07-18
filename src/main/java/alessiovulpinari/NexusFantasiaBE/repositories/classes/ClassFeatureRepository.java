package alessiovulpinari.NexusFantasiaBE.repositories.classes;

import alessiovulpinari.NexusFantasiaBE.entities.classes.ClassFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClassFeatureRepository extends JpaRepository<ClassFeature, UUID> {
    Optional<ClassFeature> findByClassFeatureName(String classFeatureName);
}
