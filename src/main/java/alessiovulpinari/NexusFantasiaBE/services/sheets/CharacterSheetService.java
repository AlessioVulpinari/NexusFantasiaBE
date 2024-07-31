package alessiovulpinari.NexusFantasiaBE.services.sheets;

import alessiovulpinari.NexusFantasiaBE.entities.User;
import alessiovulpinari.NexusFantasiaBE.entities.classes.Class;
import alessiovulpinari.NexusFantasiaBE.entities.classes.Subclass;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.Level;
import alessiovulpinari.NexusFantasiaBE.entities.races.Languages;
import alessiovulpinari.NexusFantasiaBE.entities.races.Race;
import alessiovulpinari.NexusFantasiaBE.entities.races.Subrace;
import alessiovulpinari.NexusFantasiaBE.entities.sheet.Background;
import alessiovulpinari.NexusFantasiaBE.entities.sheet.CharacterSheet;
import alessiovulpinari.NexusFantasiaBE.enums.AbilityScoreDistribution;
import alessiovulpinari.NexusFantasiaBE.enums.Alignment;
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

import java.util.*;

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

    public Page<CharacterSheet> getAllUserCharacterSheets(int pageNumber, int pageSize, User user) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return characterSheetRepository.findByUser(user, pageable);
    }

    public CharacterSheet getCharacterSheetById(UUID uuid) {
        return characterSheetRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Nessuna scheda personaggio con questo id trovata!"));
    }

    public CharacterSheet saveCharacterSheet(CharacterSheetDTO body, User user) {
        CharacterSheet characterSheet = new CharacterSheet(body.name());
        characterSheet.setUser(user);

        Background background = this.backgroundService.getBackgroundByName(body.backgroundName());

        //primo step
        characterSheet.setAge(body.age());
        characterSheet.setHeight(body.height());
        characterSheet.setWeight(body.weight());
        characterSheet.setEyeColor(body.eyeColor());
        characterSheet.setHairColor(body.hairColor());
        characterSheet.setComplexion(body.complexion());

        // secondo step
        characterSheet.setAlignment(Alignment.valueOf(body.alignment()));
        characterSheet.setPersonalTraits(body.personalTraits());
        characterSheet.setIdeals(body.ideals());
        characterSheet.setBonds(body.bonds());
        characterSheet.setFlaw(body.flaw());
        characterSheet.setBackground(background);

        characterSheet.addEquipments(background.getEquipmentList());
        characterSheet.addProficiencies(background.getProficiencies());

        if (!body.languagesSet().isEmpty() && background.getExtraLanguages() != 0) {
            characterSheet.addLanguages(body.languagesSet());
        }

        // terzo passaggio
        this.setRaceAndSubRace(characterSheet, body);

        // quarto passaggio
        Class aClass = this.classService.findByClassName(body.className());
        characterSheet.addClass(aClass);
        characterSheet.addProficiencies(aClass.getClassProficiency());
        characterSheet.addEquipments(aClass.getEquipmentList());

        Level level = classService.getClassLevelByLevelNumber(aClass.getClassId(), 1);
        characterSheet.addLevel(level);

        if (body.subclassName() != null && aClass.getLevelForSubClass() == 1) {
            Subclass subclass = this.subclassService.findByName(body.subclassName());
            characterSheet.addSubclass(subclass);

            // altrimenti aggiungiamo solamente un livello della sotto classe se c'è!
            try
            {
                characterSheet.addSubClassLevel(subclassService.subClassLevelByLevelNumber(subclass.getSubclassId(), 1));
            }
            catch (BadRequestException exception) {
                System.out.println("Non esiste il livello: " + 1 + " per questa sotto classe!" );
            }

            // Gestire la magia?
            // Magie lanciabili recuperate dal livello?
        }

        // Quinto step
        characterSheet.setAbilityScoreDistribution(AbilityScoreDistribution.valueOf(body.abilityScoreDistribution()));

        return this.characterSheetRepository.save(characterSheet);
    }

    // questa dovrà diventare il metodo per la put
    public CharacterSheet changeName(UUID characterSheetId, CharacterSheetDTO body, User user) {

        CharacterSheet found = this.getCharacterSheetById(characterSheetId);
        if (!(found.getUser().getUserId() == user.getUserId())) throw new BadRequestException("Puoi modificare solo delle schede che ti appartengono!");
        found.setName(body.name());
        Background background = this.backgroundService.getBackgroundByName(body.backgroundName());

        //primo step
        found.setAge(body.age());
        found.setHeight(body.height());
        found.setWeight(body.weight());
        found.setEyeColor(body.eyeColor());
        found.setHairColor(body.hairColor());
        found.setComplexion(body.complexion());

        // secondo step
        found.setAlignment(Alignment.valueOf(body.alignment()));
        found.setPersonalTraits(body.personalTraits());
        found.setIdeals(body.ideals());
        found.setBonds(body.bonds());
        found.setFlaw(body.flaw());

        // aggiungere metodo per rimuovere gli equipaggiamenti e le competenze del background precedente
        found.setEquipmentList(new ArrayList<>());
        found.setProficiencies(new HashSet<>());
        found.setLanguages(new HashSet<>());

        found.setBackground(background);

        found.addEquipments(background.getEquipmentList());
        found.addProficiencies(background.getProficiencies());

        if (!body.languagesSet().isEmpty() && background.getExtraLanguages() != 0) {
            found.addLanguages(body.languagesSet());
        }

        // terzo passaggio
        found.setStrength(0);
        found.setDexterity(0);
        found.setConstitution(0);
        found.setIntelligence(0);
        found.setWisdom(0);
        found.setCharisma(0);

        this.setRaceAndSubRace(found, body);

        // quarto passaggio
        found.setClassList(new HashSet<>());
        found.setLevels(new HashSet<>());

        Class aClass = this.classService.findByClassName(body.className());
        found.addClass(aClass);
        found.addProficiencies(aClass.getClassProficiency());
        found.addEquipments(aClass.getEquipmentList());

        Level level = classService.getClassLevelByLevelNumber(aClass.getClassId(), 1);
        found.addLevel(level);

        if (body.subclassName() != null && aClass.getLevelForSubClass() == 1) {
            Subclass subclass = this.subclassService.findByName(body.subclassName());
            found.addSubclass(subclass);

            // altrimenti aggiungiamo solamente un livello della sotto classe se c'è!
            try
            {
                found.addSubClassLevel(subclassService.subClassLevelByLevelNumber(subclass.getSubclassId(), 1));
            }
            catch (BadRequestException exception) {
                System.out.println("Non esiste il livello: " + 1 + " per questa sotto classe!" );
            }

            // Gestire la magia?
            // Magie lanciabili recuperate dal livello?
        }

        // Quinto step
        found.setAbilityScoreDistribution(AbilityScoreDistribution.valueOf(body.abilityScoreDistribution()));

        return this.characterSheetRepository.save(found);
    }

    public void findByIdAndDelete(UUID characterSheetId, User user) {
        CharacterSheet found = getCharacterSheetById(characterSheetId);

        if (!(found.getUser().getUserId() == user.getUserId())) throw new BadRequestException("Puoi cancellare solo delle schede che ti appartengono!");
        this.characterSheetRepository.delete(found);
    }


    public void setRaceAndSubRace(CharacterSheet found, CharacterSheetDTO body) {
        Race race = raceService.findByName(body.raceName());
        Subrace subrace = subRaceService.findByName(body.subRaceName());

        if (!subrace.getRace().equals(race)) throw new BadRequestException("La sotto razza non appartiene alla razza coretta!");

        found.setRace(race);

        found.addProficiencies(race.getProficiencies());
        found.addLanguages(race.getLanguages());

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

    }

    public int numbOfClassLevelsInACharacterSheet(UUID uuid, Class aClass) {
        return this.characterSheetRepository.classLevels(uuid, aClass).size();
    }

    // Livelli successivi all'uno
    public CharacterSheet addClassLevel(UUID characterSheetId, AddClassDTO body, User user) {
        CharacterSheet found = this.getCharacterSheetById(characterSheetId);
        if (!(found.getUser().getUserId() == user.getUserId())) throw new BadRequestException("Puoi modificare solo delle schede che ti appartengono!");

        Class aClass = this.classService.findByClassName(body.className());

        int size = numbOfClassLevelsInACharacterSheet(characterSheetId, aClass);

        if (size == 0) {
            found.addClass(aClass);
            found.addProficiencies(aClass.getClassProficiency());
            found.addEquipments(aClass.getEquipmentList());
        }

        Level level = classService.getClassLevelByLevelNumber(aClass.getClassId(), size +1);
        found.addLevel(level);

        // Bonus competenza? da aggiungere alla scheda? o recuperarlo dal livello?
        // Magie lanciabili recuperate dal livello?


        if (body.subclassName() != null && size >= aClass.getLevelForSubClass()) {
            Subclass subclass = this.subclassService.findByName(body.subclassName());

            // Se la sotto classe non c'è nella scheda la aggiungiamo
            List<Subclass> subclassSet = found.getSubclassSet().stream().filter(subclass1 -> subclass1.getName().equals(subclass.getName())).toList();
            if (subclassSet.isEmpty()) {
                found.addSubclass(subclass);
            }

            // altrimenti aggiungiamo solamente un livello della sotto classe se c'è!
            try
            {
                found.addSubClassLevel(subclassService.subClassLevelByLevelNumber(subclass.getSubclassId(), size +1));
            } catch (BadRequestException exception) {

                System.out.println("Non esiste il livello: " + size + 1 + " per questa sotto classe!" );
            }

            // Gestire la magia?
            // Magie lanciabili recuperate dal livello?
        }

        return this.characterSheetRepository.save(found);
    }

}
