package alessiovulpinari.NexusFantasiaBE.repositories.classes;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Class;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClassRepository extends JpaRepository<Class, UUID> {
    Optional<Class> findByClassName(String className);

    @Query("SELECT l FROM Class c JOIN c.classLevels l WHERE c.classId = :id AND l.levelNumber = :level")
    Optional<Level> classLevelByNumber(UUID id, int level);
}
