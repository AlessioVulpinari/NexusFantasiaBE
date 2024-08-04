package alessiovulpinari.NexusFantasiaBE.services.classes;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Class;
import alessiovulpinari.NexusFantasiaBE.entities.classes.Proficiency;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.Level;
import alessiovulpinari.NexusFantasiaBE.entities.equipments.Equipment;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.ClassDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.equipments.EquipmentNameDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.races.ProficiencyNameDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.classes.ClassRepository;
import alessiovulpinari.NexusFantasiaBE.services.equipments.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private ProficiencyService proficiencyService;

    public Page<Class> getClasses(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return classRepository.findAll(pageable);
    }

    public Class getClass(UUID uuid) {
        return classRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Classe con questo id non trovata!"));
    }

    public Class saveClass(ClassDTO body) {
        this.classRepository.findByClassName(body.className()).ifPresent
                (aClass -> {throw new BadRequestException("Esiste già un classe con questo nome: " + body.className());});

        Class newClass = new Class(body.className(), body.description(), body.hitDice(), body.levelForSubClass());

        return classRepository.save(newClass);
    }

    public Class findByIdAndUpdate(UUID classId, ClassDTO body)
    {
        Class found = getClass(classId);
        found.setClassName(body.className());
        found.setDescription(body.description());
        found.setLevelForSubClass(body.levelForSubClass());
        found.setHitDice(body.hitDice());

        return classRepository.save(found);
    }

    public void findByIdAndDelete(UUID classId) {
        Class found = getClass(classId);
        this.classRepository.delete(found);
    }

    public Class addToEquipmentList(UUID classId, EquipmentNameDTO body) {
        Class found = getClass(classId);
        Equipment equipment = this.equipmentService.findByName(body.name());

        found.addEquipment(equipment);
        return this.classRepository.save(found);
    }

    public Class removeToEquipmentList(UUID classId, EquipmentNameDTO body) {
        Class found = getClass(classId);
        Equipment equipment = this.equipmentService.findByName(body.name());

        found.removeEquipment(equipment);
        return this.classRepository.save(found);
    }

    public Class addToProficiencyList(UUID classId, ProficiencyNameDTO body) {
        Class found = getClass(classId);
        Proficiency proficiency = this.proficiencyService.findByProficiencyName(body.proficiencyName());

        found.addProficiency(proficiency);
        return this.classRepository.save(found);
    }

    public Class removeToProficiencyList(UUID classId, ProficiencyNameDTO body) {
        Class found = getClass(classId);
        Proficiency proficiency = this.proficiencyService.findByProficiencyName(body.proficiencyName());

        found.removeProficiency(proficiency);
        return this.classRepository.save(found);
    }

    public Class findByClassName(String className){
        return this.classRepository.findByClassName(className).orElseThrow(() -> new NotFoundException("Classe con nome: " + className + " non trovato!"));
    }

    public Level getClassLevelByLevelNumber(UUID uuid, int level) {
        return classRepository.classLevelByNumber(uuid, level).orElseThrow(()-> new BadRequestException("Non esiste il livello numero: " + level + " per questa classe!"));
    }
}
