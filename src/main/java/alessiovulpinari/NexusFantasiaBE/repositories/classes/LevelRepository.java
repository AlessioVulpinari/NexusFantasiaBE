package alessiovulpinari.NexusFantasiaBE.repositories.classes;


import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LevelRepository extends JpaRepository<Level, UUID> {
}
