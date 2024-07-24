package alessiovulpinari.NexusFantasiaBE.repositories.sheet;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Class;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.Level;
import alessiovulpinari.NexusFantasiaBE.entities.sheet.CharacterSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CharacterSheetRepository extends JpaRepository<CharacterSheet, UUID> {

    // query che restituisca il numero di livelli di una determinata classe di una determinata scheda personaggio
    @Query("SELECT l FROM CharacterSheet c JOIN c.levels l WHERE c.characterSheetId = :id AND l.aClass = :aClass")
    List<Level> classLevels(UUID id, Class aClass);

}
