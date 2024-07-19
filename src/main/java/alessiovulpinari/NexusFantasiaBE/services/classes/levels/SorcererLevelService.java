package alessiovulpinari.NexusFantasiaBE.services.classes.levels;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Class;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.Level;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.SorcererLevel;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.levels.SorcererLevelDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.classes.LevelRepository;
import alessiovulpinari.NexusFantasiaBE.services.classes.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SorcererLevelService {

    private LevelRepository levelRepository;

    @Autowired
    private LevelService levelService;

    @Autowired
    private ClassService classService;

    public SorcererLevel saveLevel (SorcererLevelDTO body) {

        Class aClass = this.classService.findByClassName(body.className());

        SorcererLevel sorcererLevel = new SorcererLevel(body.levelNumber(),
                body.proficiencyBonus(),
                aClass,
                body.cantripsKnown(), body.spellsKnown(), body.fistSlotSpell(),
                body.secondSlotSpell() == null ? 0 : body.secondSlotSpell(),
                body.thirdSlotSpell() == null ? 0 : body.thirdSlotSpell(),
                body.fourthSlotSpell() == null ? 0 : body.fourthSlotSpell(),
                body.fifthSlotSpell() == null ? 0 : body.fifthSlotSpell(),
                body.sixthSlotSpell() == null ? 0 : body.sixthSlotSpell(),
                body.seventhSlotSpell() == null ? 0 : body.seventhSlotSpell(),
                body.eighthSlotSpell() == null ? 0 : body.eighthSlotSpell(),
                body.ninthSlotSpell() == null ? 0 : body.ninthSlotSpell(),
                body.sorceryPoints());

        return this.levelRepository.save(sorcererLevel);
    }

    public SorcererLevel findByIdAndUpdate(UUID sorcererLevelId, SorcererLevelDTO body) {

        Level found = this.levelService.getLevelById(sorcererLevelId);
        Class aClass = classService.findByClassName(body.className());

        if (!(found instanceof SorcererLevel level)) throw new BadRequestException("L'id inserito non è di un livello da stregone!");

        level.setLevelNumber(body.levelNumber());
        level.setProficiencyBonus(body.proficiencyBonus());
        level.setAClass(aClass);
        level.setCantripsKnown(body.cantripsKnown());
        level.setFistSlotSpell(body.fistSlotSpell());
        level.setSecondSlotSpell(body.secondSlotSpell() == null ? 0 : body.secondSlotSpell());
        level.setThirdSlotSpell(body.thirdSlotSpell() == null ? 0 : body.thirdSlotSpell());
        level.setFourthSlotSpell(body.fourthSlotSpell() == null ? 0 : body.fourthSlotSpell());
        level.setFifthSlotSpell(body.fifthSlotSpell() == null ? 0 : body.fifthSlotSpell());
        level.setSixthSlotSpell(body.sixthSlotSpell() == null ? 0 : body.sixthSlotSpell());
        level.setSeventhSlotSpell(body.seventhSlotSpell() == null ? 0 : body.seventhSlotSpell());
        level.setEighthSlotSpell(body.eighthSlotSpell() == null ? 0 : body.eighthSlotSpell());
        level.setNinthSlotSpell(body.ninthSlotSpell() == null ? 0 : body.ninthSlotSpell());
        level.setSorceryPoints(body.sorceryPoints());

        return this.levelRepository.save(level);
    }

}
