package alessiovulpinari.NexusFantasiaBE.services.races;

import alessiovulpinari.NexusFantasiaBE.entities.races.Languages;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.races.LanguageDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.races.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    public Page<Languages> getLanguages(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return languageRepository.findAll(pageable);
    }

    public Languages getLanguageById(UUID uuid) {
        return languageRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Nessun linguaggio con questo id trovato!"));
    }

    public Languages saveLanguage(LanguageDTO body) {
        this.languageRepository.findByName(body.name()).ifPresent
                (aClass -> {throw new BadRequestException("Esiste già un linguaggio con questo nome: " + body.name());});

        Languages languages = new Languages(body.name(), body.description());
        return  this.languageRepository.save(languages);
    }

    public Languages findByIdAndUpdate(UUID languageId, LanguageDTO body) {

        Languages found = this.getLanguageById(languageId);
        found.setName(body.name());
        found.setDescription(body.description());

        return this.languageRepository.save(found);
    }

    public void findByIdAndDelete(UUID languageId) {
        Languages found = getLanguageById(languageId);
        this.languageRepository.delete(found);
    }
}
