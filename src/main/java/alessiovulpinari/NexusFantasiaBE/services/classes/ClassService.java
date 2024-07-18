package alessiovulpinari.NexusFantasiaBE.services.classes;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Class;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.ClassDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.classes.ClassRepository;
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

        Class newClass = new Class(body.className(), body.hitDice(), body.levelForSubClass());

        return classRepository.save(newClass);
    }

    public Class findByIdAndUpdate(UUID classId, ClassDTO body)
    {
        Class found = getClass(classId);
        found.setClassName(body.className());
        found.setLevelForSubClass(body.levelForSubClass());
        found.setHitDice(body.hitDice());

        return classRepository.save(found);
    }

    public void findByIdAndDelete(UUID classId) {
        Class found = getClass(classId);
        this.classRepository.delete(found);
    }

    public Class findByClassName(String className){
        return this.classRepository.findByClassName(className).orElseThrow(() -> new NotFoundException("Classe con nome: " + className + " non trovato!"));
    }
}
