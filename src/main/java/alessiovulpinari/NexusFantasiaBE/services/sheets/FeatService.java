package alessiovulpinari.NexusFantasiaBE.services.sheets;

import alessiovulpinari.NexusFantasiaBE.entities.sheet.Feat;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.sheets.FeatDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.sheet.FeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FeatService {

    @Autowired
    private FeatRepository featRepository;

    public Page<Feat> getFeats(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return featRepository.findAll(pageable);
    }

    public Feat getFeatById(UUID uuid) {
        return featRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Nessun talento con questo id trovato!"));
    }

    public Feat saveFeat(FeatDTO body) {
        this.featRepository.findByName(body.name()).ifPresent
                (feature -> {throw new BadRequestException("Esiste già un talento con questo nome: " + body.name());});

        Feat feat = new Feat(body.name() ,body.prerequisite(), body.description());
        return this.featRepository.save(feat);
    }

    public Feat findByIdAndUpdate(UUID featId, FeatDTO body) {

        Feat found = this.getFeatById(featId);
        found.setName(body.name());
        found.setDescription(body.description());
        found.setPrerequisite(body.prerequisite());

        return this.featRepository.save(found);
    }

    public void findByIdAndDelete(UUID featId) {
        Feat found = getFeatById(featId);
        this.featRepository.delete(found);
    }

    public Feat findByName(String name) {
        return featRepository.findByName(name).orElseThrow(() -> new NotFoundException("Talento con nome: " + name + " non trovato!"));
    }
}
