package alessiovulpinari.NexusFantasiaBE.services.classes.levels;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Class;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.Level;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.MonkLevel;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.RogueLevel;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.levels.MonkLevelDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.levels.RogueLevelDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.classes.LevelRepository;
import alessiovulpinari.NexusFantasiaBE.services.classes.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RogueLevelService {

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private LevelService levelService;

    @Autowired
    private ClassService classService;

    public RogueLevel saveLevel(RogueLevelDTO body) {

        Class found = this.classService.findByClassName(body.className());
        RogueLevel newLevel = new RogueLevel(body.levelNumber(), body.proficiencyBonus(),
                found, body.sneakAttackNumberDice(), body.sneakAttackDamageDice());

        return levelRepository.save(newLevel);
    }

    public RogueLevel findByIdAndUpdate(UUID rogueLevelId, RogueLevelDTO body)
    {
        Level found = this.levelService.getLevelById(rogueLevelId);

        if (!(found instanceof RogueLevel level)) throw new BadRequestException("L'id inserito non è di un livello da ladro!");

        level.setLevelNumber(body.levelNumber());
        level.setProficiencyBonus(body.proficiencyBonus());
        level.setAClass(classService.findByClassName(body.className()));

        level.setSneakAttackNumberDice(body.sneakAttackNumberDice());
        level.setSneakAttackDamageDice(body.sneakAttackDamageDice());

        return levelRepository.save(level);
    }
}
