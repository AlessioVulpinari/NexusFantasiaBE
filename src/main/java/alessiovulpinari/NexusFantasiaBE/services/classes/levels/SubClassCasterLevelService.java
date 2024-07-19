package alessiovulpinari.NexusFantasiaBE.services.classes.levels;

import alessiovulpinari.NexusFantasiaBE.entities.classes.ClassFeature;
import alessiovulpinari.NexusFantasiaBE.entities.classes.Subclass;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.CasterLevel;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.SubClassCasterLevel;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.SubclassLevel;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.levels.SubClassCasterLevelDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.levels.SubClassLevelDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.classes.SubclassLevelRepository;
import alessiovulpinari.NexusFantasiaBE.services.classes.ClassFeatureService;
import alessiovulpinari.NexusFantasiaBE.services.classes.SubclassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubClassCasterLevelService {

    @Autowired
    private SubclassLevelRepository subclassLevelRepository;

    @Autowired
    private SubClassLevelService subClassLevelService;

    @Autowired
    private ClassFeatureService classFeatureService;

    @Autowired
    private SubclassService subclassService;

    public SubClassCasterLevel saveSubClassCasterLevel(SubClassCasterLevelDTO body) {

        ClassFeature foundClassFeature = this.classFeatureService.findByFeatureName(body.classFeature());
        Subclass foundClass = this.subclassService.findByName(body.subClassName());
        SubClassCasterLevel newSubclassLevel = new SubClassCasterLevel(body.levelNumber(), foundClassFeature, foundClass, body.cantripsKnown(), body.spellsKnown(), body.fistSlotSpell(),
                body.secondSlotSpell() == null ? 0 : body.secondSlotSpell(),
                body.thirdSlotSpell() == null ? 0 : body.thirdSlotSpell(),
                body.fourthSlotSpell() == null ? 0 : body.fourthSlotSpell());

        return subclassLevelRepository.save(newSubclassLevel);
    }

    public SubClassCasterLevel findByIdAndUpdate(UUID levelId, SubClassCasterLevelDTO body)
    {
        SubclassLevel found = this.subClassLevelService.getLevelById(levelId);

        if (!(found instanceof SubClassCasterLevel level)) throw new BadRequestException("L'id inserito non è di un livello da incantatore!");

        ClassFeature foundClassFeature = this.classFeatureService.findByFeatureName(body.classFeature());
        Subclass foundClass = this.subclassService.findByName(body.subClassName());

        level.setLevelNumber(body.levelNumber());
        level.setClassFeature(foundClassFeature);
        level.setSubclass(foundClass);
        level.setCantripsKnown(body.cantripsKnown());
        level.setSpellsKnown(body.spellsKnown());
        level.setFistSlotSpell(body.fistSlotSpell());
        level.setSecondSlotSpell(body.secondSlotSpell() == null ? 0 : body.secondSlotSpell());
        level.setThirdSlotSpell(body.thirdSlotSpell() == null ? 0 : body.thirdSlotSpell());
        level.setFourthSlotSpell(body.fourthSlotSpell() == null ? 0 : body.fourthSlotSpell());

        return subclassLevelRepository.save(level);
    }
}
