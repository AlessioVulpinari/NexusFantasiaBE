package alessiovulpinari.NexusFantasiaBE.services.classes;

import alessiovulpinari.NexusFantasiaBE.entities.classes.ClassFeature;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.ClassFeatureDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.classes.ClassFeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClassFeatureService {

    @Autowired
    private ClassFeatureRepository classFeatureRepository;

    public Page<ClassFeature> getClassFeatures(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return classFeatureRepository.findAll(pageable);
    }

    public ClassFeature getClassFeature(UUID uuid) {
        return classFeatureRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Tratto con questo id non trovata!"));
    }

    public ClassFeature saveClass(ClassFeatureDTO body) {
        this.classFeatureRepository.findByClassFeatureName(body.classFeatureName()).ifPresent
                (feature -> {throw new BadRequestException("Esiste già un tratto con questo nome: " + body.classFeatureName());});

        ClassFeature newClass = new ClassFeature();

        return classFeatureRepository.save(newClass);
    }

    public ClassFeature findByIdAndUpdate(UUID featureId, ClassFeatureDTO body)
    {
        ClassFeature found = this.getClassFeature(featureId);
        found.setClassFeatureName(body.classFeatureName());
        found.setClassFeatureDescription(body.classFeatureDescription());

        return classFeatureRepository.save(found);
    }

    public void findByIdAndDelete(UUID featureId) {
        ClassFeature found = this.getClassFeature(featureId);
        this.classFeatureRepository.delete(found);
    }

    public ClassFeature findByFeatureName(String classFeatureName) {
        return this.classFeatureRepository.findByClassFeatureName(classFeatureName).orElseThrow(() -> new NotFoundException("Nessuno tratto con questo nome: " + classFeatureName + " trovato!"));
    }

}
