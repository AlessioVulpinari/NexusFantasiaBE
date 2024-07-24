package alessiovulpinari.NexusFantasiaBE.services.sheets;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Class;
import alessiovulpinari.NexusFantasiaBE.entities.classes.Subclass;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.Level;
import alessiovulpinari.NexusFantasiaBE.entities.races.Languages;
import alessiovulpinari.NexusFantasiaBE.entities.races.Race;
import alessiovulpinari.NexusFantasiaBE.entities.races.Subrace;
import alessiovulpinari.NexusFantasiaBE.entities.sheet.Background;
import alessiovulpinari.NexusFantasiaBE.entities.sheet.CharacterSheet;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.sheets.*;
import alessiovulpinari.NexusFantasiaBE.repositories.sheet.CharacterSheetRepository;
import alessiovulpinari.NexusFantasiaBE.services.classes.ClassService;
import alessiovulpinari.NexusFantasiaBE.services.classes.SubclassService;
import alessiovulpinari.NexusFantasiaBE.services.races.RaceService;
import alessiovulpinari.NexusFantasiaBE.services.races.SubRaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class CharacterSheetService {

    @Autowired
    private CharacterSheetRepository characterSheetRepository;

    @Autowired
    private BackgroundService backgroundService;

    @Autowired
    private RaceService raceService;

    @Autowired
    private SubRaceService subRaceService;

    @Autowired
    private ClassService classService;

    @Autowired
    private SubclassService subclassService;

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

    public CharacterSheet changeName(UUID characterSheetId, CharacterSheetDTO body) {

        CharacterSheet found = this.getCharacterSheetById(characterSheetId);
        found.setName(body.name());

        return this.characterSheetRepository.save(found);
    }

    public void findByIdAndDelete(UUID characterSheetId) {
        CharacterSheet found = getCharacterSheetById(characterSheetId);
        this.characterSheetRepository.delete(found);
    }

    public CharacterSheet changePhysicalTraits(UUID characterSheetId, PhysicalTraitsDTO body) {
        CharacterSheet found = this.getCharacterSheetById(characterSheetId);

        found.setAge(body.age());
        found.setHeight(body.height());
        found.setWeight(body.weight());
        found.setEyeColor(body.eyeColor());
        found.setHairColor(body.hairColor());
        found.setComplexion(body.complexion());

        return this.characterSheetRepository.save(found);
    }

    // Valuta se aggiungere direttamente al DTO la lista di linguaggi
    public CharacterSheet changePersonalTraits(UUID characterSheetId, PersonalTraitsDTO body, Set<Languages> languagesSet) {
        CharacterSheet found = this.getCharacterSheetById(characterSheetId);
        Background background = this.backgroundService.getBackgroundByName(body.backgroundName());

        found.setPersonalTraits(body.personalTraits());
        found.setIdeals(body.ideals());
        found.setBonds(body.bonds());
        found.setFlaw(body.flaw());
        found.setBackground(background);

        // Da prendere nel DB?
        found.addEquipments(background.getEquipmentList());
        found.addProficiencies(background.getProficiencies());

        if (!languagesSet.isEmpty() && background.getExtraLanguages() != 0) {
            found.addLanguages(languagesSet);
        }

        return this.characterSheetRepository.save(found);
    }

    public CharacterSheet setRaceAndSubRace(UUID characterSheetId, AddRaceDTO body) {
        CharacterSheet found = this.getCharacterSheetById(characterSheetId);

        Race race = raceService.findByName(body.raceName());
        Subrace subrace = subRaceService.findByName(body.subRaceName());

        if (!subrace.getRace().equals(race)) throw new BadRequestException("La sotto razza non appartiene alla razza coretta!");

        found.setRace(race);

        found.addProficiencies(race.getProficiencies());
        found.addLanguages(race.getLanguages());

        // Un modo migliore ?
        race.getIncrementedScoreMap().forEach(incrementedScore -> {
            switch (incrementedScore.getAbilityScore().getName()) {
                case "forza":
                    found.setStrength(found.getStrength() + incrementedScore.getIncrementValue());
                    break;

                case "destrezza":
                    found.setDexterity(found.getDexterity() + incrementedScore.getIncrementValue());
                    break;

                case "costituzione":
                    found.setConstitution(found.getConstitution() + incrementedScore.getIncrementValue());
                    break;

                case "intelligenza":
                    found.setIntelligence(found.getIntelligence() + incrementedScore.getIncrementValue());
                    break;

                case "saggezza":
                    found.setWisdom(found.getWisdom() + incrementedScore.getIncrementValue());
                    break;

                case "carisma":
                    found.setCharisma(found.getCharisma() + incrementedScore.getIncrementValue());
                    break;

                default:
                    throw new BadRequestException("L'incremento non corrisponde a nessuna caratteristica! ");
            }
        });

        found.setSubrace(subrace);
        subrace.getIncrementedScoreMap().forEach(incrementedScore -> {
            switch (incrementedScore.getAbilityScore().getName()) {
                case "forza":
                    found.setStrength(found.getStrength() + incrementedScore.getIncrementValue());
                    break;

                case "destrezza":
                    found.setDexterity(found.getDexterity() + incrementedScore.getIncrementValue());
                    break;

                case "costituzione":
                    found.setConstitution(found.getConstitution() + incrementedScore.getIncrementValue());
                    break;

                case "intelligenza":
                    found.setIntelligence(found.getIntelligence() + incrementedScore.getIncrementValue());
                    break;

                case "saggezza":
                    found.setWisdom(found.getWisdom() + incrementedScore.getIncrementValue());
                    break;

                case "carisma":
                    found.setCharisma(found.getCharisma() + incrementedScore.getIncrementValue());
                    break;

                default:
                    throw new BadRequestException("L'incremento non corrisponde a nessuna caratteristica! ");
            }
        });

        found.addLanguages(subrace.getLanguages());

        return this.characterSheetRepository.save(found);
    }

    public int numbOfClassInACharacterSheet(UUID uuid, Class aClass) {
        return this.characterSheetRepository.classLevels(uuid, aClass).size();
    }

    public CharacterSheet addClassLevel(UUID characterSheetId, AddClassDTO body) {
        CharacterSheet found = this.getCharacterSheetById(characterSheetId);

        Class aClass = this.classService.findByClassName(body.className());

        int size = numbOfClassInACharacterSheet(characterSheetId, aClass);

        if (size == 0) {
            found.addClass(aClass);
            found.addProficiencies(aClass.getClassProficiency());
            found.addEquipments(aClass.getEquipmentList());
        }

        // Aggiungere livello classe

        if (body.subclassName() != null && size >= aClass.getLevelForSubClass()) {
            Subclass subclass = this.subclassService.findByName(body.subclassName());

            // Aggiungere bonus sottoclasse 
        }



        return this.characterSheetRepository.save(found);
    }



    // Livello 1
    /*TODO AGGIUNGI METODI PER AGGIUNGERE LE INFO ALLA SCHEDA (METODO PER RESTITUIRE LE SCHEDE DELL'UTENTE LOGGATO,
       metodo per scegliere la classe e la sottoclasse
       metodo per scegliere la distribuzione delle statistiche,
     */


}
