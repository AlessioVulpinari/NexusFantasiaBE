package alessiovulpinari.NexusFantasiaBE.services.sheets;

import alessiovulpinari.NexusFantasiaBE.entities.sheet.BackgroundFeature;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.sheets.BackgroundFeatureDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.sheet.BackgroundFeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BackgroundFeatureService {

    @Autowired
    private BackgroundFeatureRepository backgroundFeatureRepository;

    public Page<BackgroundFeature> getBackgroundFeatures(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return backgroundFeatureRepository.findAll(pageable);
    }

    public BackgroundFeature getBackgroundFeatureById(UUID uuid) {
        return backgroundFeatureRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Nessun tratto background con questo id trovato!"));
    }

    public BackgroundFeature saveBackgroundFeature(BackgroundFeatureDTO body) {
        this.backgroundFeatureRepository.findByName(body.name()).ifPresent
                (feature -> {throw new BadRequestException("Esiste già un linguaggio con questo nome: " + body.name());});

        BackgroundFeature feature = new BackgroundFeature(body.name(), body.description());
        return  this.backgroundFeatureRepository.save(feature);
    }

    public BackgroundFeature findByIdAndUpdate(UUID backFeatureId, BackgroundFeatureDTO body) {

        BackgroundFeature found = this.getBackgroundFeatureById(backFeatureId);
        found.setName(body.name());
        found.setDescription(body.description());

        return this.backgroundFeatureRepository.save(found);
    }

    public void findByIdAndDelete(UUID backFeatureId) {
        BackgroundFeature found = getBackgroundFeatureById(backFeatureId);
        this.backgroundFeatureRepository.delete(found);
    }

    public BackgroundFeature findByName(String name) {
        return this.backgroundFeatureRepository.findByName(name).orElseThrow(()-> new NotFoundException("Tratto razziale non trovato!"));
    }

}
