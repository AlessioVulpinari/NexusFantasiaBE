package alessiovulpinari.NexusFantasiaBE.services.classes.levels;

import alessiovulpinari.NexusFantasiaBE.entities.classes.ClassFeature;
import alessiovulpinari.NexusFantasiaBE.entities.classes.Subclass;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.SubclassLevel;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.levels.SubClassLevelDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.classes.SubclassLevelRepository;
import alessiovulpinari.NexusFantasiaBE.services.classes.ClassFeatureService;
import alessiovulpinari.NexusFantasiaBE.services.classes.SubclassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubClassLevelService {

    @Autowired
    private SubclassLevelRepository subclassLevelRepository;

    @Autowired
    private ClassFeatureService classFeatureService;

    @Autowired
    private SubclassService subclassService;

    public Page<SubclassLevel> getLevels(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return subclassLevelRepository.findAll(pageable);
    }

    public SubclassLevel getLevelById(UUID uuid) {
        return subclassLevelRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Livello con questo id non trovata!"));
    }

    public SubclassLevel saveClassLevel(SubClassLevelDTO body) {

        ClassFeature foundClassFeature = this.classFeatureService.findByFeatureName(body.classFeature());
        Subclass foundClass = this.subclassService.findByName(body.subClassName());
        SubclassLevel newSubclassLevel = new SubclassLevel(body.levelNumber(), foundClassFeature, foundClass);

        return subclassLevelRepository.save(newSubclassLevel);
    }


    public SubclassLevel findByIdAndUpdate(UUID levelId, SubClassLevelDTO body)
    {
        SubclassLevel found = this.getLevelById(levelId);

        ClassFeature foundClassFeature = this.classFeatureService.findByFeatureName(body.classFeature());
        Subclass foundClass = this.subclassService.findByName(body.subClassName());

        found.setLevelNumber(body.levelNumber());
        found.setClassFeature(foundClassFeature);
        found.setSubclass(foundClass);

        return subclassLevelRepository.save(found);
    }

    public void findByIdAndDelete(UUID levelId) {
        SubclassLevel found = this.getLevelById(levelId);
        this.subclassLevelRepository.delete(found);
    }
}
