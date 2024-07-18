package alessiovulpinari.NexusFantasiaBE.repositories.classes;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Proficiency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProficiencyRepository extends JpaRepository<Proficiency, UUID> {
}
