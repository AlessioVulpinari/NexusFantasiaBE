package alessiovulpinari.NexusFantasiaBE.repositories.classes;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Subclass;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.Level;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.SubclassLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubclassRepository extends JpaRepository<Subclass, UUID> {
    Optional<Subclass> findByName(String name);

    @Query("SELECT l FROM Subclass s JOIN s.subClassLevels l WHERE s.subclassId = :id AND l.levelNumber = :level")
    Optional<SubclassLevel> classLevelByNumber(UUID id, int level);
}
