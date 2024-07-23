package alessiovulpinari.NexusFantasiaBE.repositories.sheet;

import alessiovulpinari.NexusFantasiaBE.entities.sheet.CharacterSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CharacterSheetRepository extends JpaRepository<CharacterSheet, UUID> {
}
