package alessiovulpinari.NexusFantasiaBE.services.classes;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Class;
import alessiovulpinari.NexusFantasiaBE.entities.classes.Subclass;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.Level;
import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.SubclassLevel;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.SubclassDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.classes.SubclassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubclassService {

    @Autowired
    private SubclassRepository subclassRepository;

    @Autowired
    private ClassService classService;

    public Page<Subclass> getSubclass(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return subclassRepository.findAll(pageable);
    }

    public Subclass getSubClass(UUID uuid) {
        return subclassRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Classe con questo id non trovata!"));
    }

    public Subclass saveClass(SubclassDTO body) {

        this.subclassRepository.findByName(body.name()).ifPresent
                (aClass -> {throw new BadRequestException("Esiste già un sotto classe con questo nome: " + body.className());});

        Class found = this.classService.findByClassName(body.className());

        Subclass newSubclass = new Subclass(body.name(), body.description(), found);

        return subclassRepository.save(newSubclass);
    }

    public Subclass findByIdAndUpdate(UUID subClassId, SubclassDTO body)
    {
        Subclass found = getSubClass(subClassId);
        Class aClass = this.classService.findByClassName(body.className());

        found.setName(body.name());
        found.setDescription(body.description());
        found.setAClass(aClass);

        return subclassRepository.save(found);
    }

    public void findByIdAndDelete(UUID subClassId) {
        Subclass found = getSubClass(subClassId);
        this.subclassRepository.delete(found);
    }

    public Subclass findByName(String subclassName) {
        return subclassRepository.findByName(subclassName).orElseThrow(() -> new NotFoundException("Sotto classe con nome: " + subclassName + " non trovata!"));
    }

    public SubclassLevel subClassLevelByLevelNumber(UUID subclassId, int levelNumber) {
        return subclassRepository.classLevelByNumber(subclassId, levelNumber).orElseThrow(()-> new BadRequestException("Livello sotto classe non trovato!"));
    }


}
