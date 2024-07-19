package alessiovulpinari.NexusFantasiaBE.services.classes.levels;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Class;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.Level;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.MonkLevel;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.WarlockLevel;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.levels.MonkLevelDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.levels.WarlockLevelDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.classes.LevelRepository;
import alessiovulpinari.NexusFantasiaBE.services.classes.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WarlockLevelService {

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private LevelService levelService;

    @Autowired
    private ClassService classService;

    public WarlockLevel saveLevel(WarlockLevelDTO body) {

        Class found = this.classService.findByClassName(body.className());
        WarlockLevel newLevel = new WarlockLevel(body.levelNumber(), body.proficiencyBonus(),
                found, body.cantripsKnown(), body.spellsKnown(),
                body.spellSlots(), body.slotLevel(), body.invocationsKnown());

        return levelRepository.save(newLevel);
    }

    public WarlockLevel findByIdAndUpdate(UUID warlockLevelId, WarlockLevelDTO body)
    {
        Level found = this.levelService.getLevelById(warlockLevelId);

        if (!(found instanceof WarlockLevel level)) throw new BadRequestException("L'id inserito non è di un livello da warlock!");

        level.setLevelNumber(body.levelNumber());
        level.setProficiencyBonus(body.proficiencyBonus());
        level.setAClass(classService.findByClassName(body.className()));

        level.setCantripsKnown(body.cantripsKnown());
        level.setSpellsKnown(body.spellsKnown());
        level.setSpellSlots(body.spellSlots());
        level.setSlotLevel(body.slotLevel());
        level.setInvocationsKnown(body.invocationsKnown());

        return levelRepository.save(level);
    }

}
