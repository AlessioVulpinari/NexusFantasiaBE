package alessiovulpinari.NexusFantasiaBE.repositories.classes;

import alessiovulpinari.NexusFantasiaBE.entities.classes.SubclassLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubclassLevelRepository extends JpaRepository<SubclassLevel, UUID> {
}
