package alessiovulpinari.NexusFantasiaBE.services.classes.levels;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Class;
import alessiovulpinari.NexusFantasiaBE.entities.classes.ClassFeature;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.Level;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.ClassFeatureNameDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.levels.LevelDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.classes.LevelRepository;
import alessiovulpinari.NexusFantasiaBE.services.classes.ClassFeatureService;
import alessiovulpinari.NexusFantasiaBE.services.classes.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LevelService {

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private ClassService classService;

    @Autowired
    private ClassFeatureService classFeatureService;

    public Page<Level> getLevels(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return levelRepository.findAll(pageable);
    }

    public Level getLevelById(UUID uuid) {
        return levelRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Livello con questo id non trovata!"));
    }

    public Level saveLevel(LevelDTO body) {

        Class found = this.classService.findByClassName(body.className());
        Level newLevel = new Level(body.levelNumber(), body.proficiencyBonus(), found );

        return levelRepository.save(newLevel);
    }

    public Level findByIdAndUpdate(UUID levelId, LevelDTO body)
    {
        Level found = this.getLevelById(levelId);
        found.setLevelNumber(body.levelNumber());
        found.setProficiencyBonus(body.proficiencyBonus());
        found.setAClass(classService.findByClassName(body.className()));

        return levelRepository.save(found);
    }

    public void findByIdAndDelete(UUID levelId) {
        Level found = this.getLevelById(levelId);
        this.levelRepository.delete(found);
    }

    public Level addClassFeature(UUID levelId, ClassFeatureNameDTO body) {
        Level found =  this.getLevelById(levelId);
        ClassFeature classFeature = this.classFeatureService.findByFeatureName(body.name());

        found.addClassFeature(classFeature);
        return levelRepository.save(found);
    }
}
