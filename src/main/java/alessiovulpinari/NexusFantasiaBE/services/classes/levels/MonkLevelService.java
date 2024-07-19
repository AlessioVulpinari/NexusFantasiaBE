package alessiovulpinari.NexusFantasiaBE.services.classes.levels;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Class;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.Level;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.MonkLevel;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.levels.MonkLevelDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.classes.LevelRepository;
import alessiovulpinari.NexusFantasiaBE.services.classes.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MonkLevelService {

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private LevelService levelService;

    @Autowired
    private ClassService classService;

    public MonkLevel saveLevel(MonkLevelDTO body) {

        Class found = this.classService.findByClassName(body.className());
        MonkLevel newLevel = new MonkLevel(body.levelNumber(), body.proficiencyBonus(),
                found, body.martialArt(), body.kiPoints(), body.unarmedMovement());

        return levelRepository.save(newLevel);
    }

    public MonkLevel findByIdAndUpdate(UUID monkLevelId, MonkLevelDTO body)
    {
        Level found = this.levelService.getLevelById(monkLevelId);

        if (!(found instanceof MonkLevel level)) throw new BadRequestException("L'id inserito non è di un livello da monaco!");

        level.setLevelNumber(body.levelNumber());
        level.setProficiencyBonus(body.proficiencyBonus());
        level.setAClass(classService.findByClassName(body.className()));

        level.setMartialArt(body.martialArt());
        level.setKiPoints(body.kiPoints());
        level.setUnarmedMovement(body.unarmedMovement());

        return levelRepository.save(level);
    }
}
