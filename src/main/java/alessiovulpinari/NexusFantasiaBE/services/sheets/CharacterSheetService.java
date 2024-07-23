package alessiovulpinari.NexusFantasiaBE.services.sheets;

import alessiovulpinari.NexusFantasiaBE.entities.sheet.CharacterSheet;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.sheets.CharacterSheetDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.sheet.CharacterSheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CharacterSheetService {

    @Autowired
    private CharacterSheetRepository characterSheetRepository;

    public Page<CharacterSheet> getCharacterSheets(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return characterSheetRepository.findAll(pageable);
    }

    public CharacterSheet getCharacterSheetById(UUID uuid) {
        return characterSheetRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Nessuna scheda personaggio con questo id trovata!"));
    }

    public CharacterSheet saveCharacterSheet(CharacterSheetDTO body) {
        CharacterSheet characterSheet = new CharacterSheet(body.name());
        return this.characterSheetRepository.save(characterSheet);
    }

    public CharacterSheet findByIdAndUpdate(UUID characterSheetId, CharacterSheetDTO body) {

        CharacterSheet found = this.getCharacterSheetById(characterSheetId);
        found.setName(body.name());

        return this.characterSheetRepository.save(found);
    }

    public void findByIdAndDelete(UUID characterSheetId) {
        CharacterSheet found = getCharacterSheetById(characterSheetId);
        this.characterSheetRepository.delete(found);
    }

}
