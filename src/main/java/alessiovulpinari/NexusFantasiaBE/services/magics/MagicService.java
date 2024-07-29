package alessiovulpinari.NexusFantasiaBE.services.magics;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Class;
import alessiovulpinari.NexusFantasiaBE.entities.classes.Subclass;
import alessiovulpinari.NexusFantasiaBE.entities.magics.Magic;
import alessiovulpinari.NexusFantasiaBE.entities.magics.MagicComponent;
import alessiovulpinari.NexusFantasiaBE.enums.MagicSchool;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.ClassNameDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.magics.MagicComponentNameDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.magics.MagicDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.magics.MagicRepository;
import alessiovulpinari.NexusFantasiaBE.services.classes.ClassService;
import alessiovulpinari.NexusFantasiaBE.services.classes.SubclassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MagicService {

    @Autowired
    private MagicRepository magicRepository;

    @Autowired
    private MagicComponentService magicComponentService;

    @Autowired
    private ClassService classService;

    @Autowired
    private SubclassService subclassService;

    public Page<Magic> getMagics(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return magicRepository.findAll(pageable);
    }

    public Magic getMagicById(UUID uuid) {
        return magicRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Magia con questo id non trovato!"));
    }

    public Magic saveMagic(MagicDTO body) {
        this.magicRepository.findByName(body.name()).ifPresent
                (aClass -> {throw new BadRequestException("Esiste già una magia con questo nome: " + body.name());});

        Magic magic = new Magic(body.name(), body.description(), body.level(), MagicSchool.valueOf(body.magicSchool()),
                body.castingTime(), body.range(), body.duration(), body.atHigherLevels(), body.isRitual());

        return magicRepository.save(magic);
    }

    public Magic findByIdAndUpdate(UUID magicId, MagicDTO body)
    {
        Magic found = getMagicById(magicId);
        found.setName(body.name());
        found.setDescription(body.description());
        found.setLevel(body.level());
        found.setMagicSchool(MagicSchool.valueOf(body.magicSchool()));
        found.setCastingTime(body.castingTime());
        found.setRange(body.range());
        found.setDuration(body.duration());
        found.setAtHigherLevels(body.atHigherLevels());
        found.setIsRitual(body.isRitual());

        return magicRepository.save(found);
    }

    public void findByIdAndDelete(UUID magicId) {
        Magic found = getMagicById(magicId);
        this.magicRepository.delete(found);
    }

    public Magic findByMagicName(String name){
        return this.magicRepository.findByName(name).orElseThrow(() -> new NotFoundException("Magia con nome: " + name + " non trovata!"));
    }

    public Magic addMagicComponent(UUID magicId, MagicComponentNameDTO body) {
        Magic found = this.getMagicById(magicId);
        MagicComponent component = magicComponentService.findByComponentName(body.magicComponentName());
        found.addMagicComponent(component);
        return this.magicRepository.save(found);
    }

    public Magic removeMagicComponent(UUID magicId, MagicComponentNameDTO body) {
        Magic found = this.getMagicById(magicId);
        MagicComponent component = magicComponentService.findByComponentName(body.magicComponentName());
        found.removeMagicComponent(component);
        return this.magicRepository.save(found);
    }

    public Magic addClassToSpellList(UUID magicId, ClassNameDTO body) {
        Magic found = this.getMagicById(magicId);
        Class aClass = classService.findByClassName(body.className());
        found.addClassToSpellList(aClass);
        return this.magicRepository.save(found);
    }

    public Magic removeClassToSpellList(UUID magicId, ClassNameDTO body) {
        Magic found = this.getMagicById(magicId);
        Class aClass = classService.findByClassName(body.className());
        found.removeClassToSpellList(aClass);
        return this.magicRepository.save(found);
    }

    public Magic addSubClassToSpellList(UUID magicId, ClassNameDTO body) {
        Magic found = this.getMagicById(magicId);
        Subclass aClass = subclassService.findByName(body.className());
        found.addSubClassToSpellList(aClass);
        return this.magicRepository.save(found);
    }

    public Magic removeSubClassToSpellList(UUID magicId, ClassNameDTO body) {
        Magic found = this.getMagicById(magicId);
        Subclass aClass = subclassService.findByName(body.className());
        found.removeSubClassToSpellList(aClass);
        return this.magicRepository.save(found);
    }

}
