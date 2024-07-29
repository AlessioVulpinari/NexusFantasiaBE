package alessiovulpinari.NexusFantasiaBE.services.races;

import alessiovulpinari.NexusFantasiaBE.entities.races.Languages;
import alessiovulpinari.NexusFantasiaBE.entities.races.Race;
import alessiovulpinari.NexusFantasiaBE.entities.races.RacialTraits;
import alessiovulpinari.NexusFantasiaBE.entities.races.Subrace;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.races.LanguageNameDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.races.RacialTraitNameDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.races.SubRaceDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.races.SubraceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubRaceService {

    @Autowired
    private SubraceRepository subraceRepository;

    @Autowired
    private CommonRaceService commonRaceService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private RacialTraitService racialTraitService;

    public Page<Subrace> getSubRaces(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return subraceRepository.findAll(pageable);
    }

    public Subrace getSubRaceById(UUID uuid) {
        return subraceRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Nessuna sotto razza con questo id trovata!"));
    }

    public Subrace saveSubRace(SubRaceDTO body) {
        this.subraceRepository.findByName(body.name()).ifPresent
                (subrace -> {throw new BadRequestException("Esiste già una sotto razza con questo nome: " + body.name());});

        Race race = commonRaceService.findRaceByName(body.raceName());

        Subrace subrace = new Subrace(body.name(), body.description(), race);
         return subraceRepository.save(subrace);
    }

    public Subrace findByIdAndUpdate(UUID subRaceId, SubRaceDTO body) {

        Subrace found = this.getSubRaceById(subRaceId);
        Race race = this.commonRaceService.findRaceByName(body.raceName());

        found.setName(body.name());
        found.setDescription(body.description());
        found.setRace(race);

        return this.subraceRepository.save(found);
    }

    public void findByIdAndDelete(UUID subRaceId) {
        Subrace found = getSubRaceById(subRaceId);
        this.subraceRepository.delete(found);
    }

    public Subrace addRacialTrait(UUID subRaceId, RacialTraitNameDTO body) {
        Subrace subrace = getSubRaceById(subRaceId);
        RacialTraits racialTraits = this.racialTraitService.findByName(body.racialTraitName());
        subrace.addRacialTrait(racialTraits);
        return this.subraceRepository.save(subrace);
    }

    public Subrace removeRacialTrait(UUID subRaceId, RacialTraitNameDTO body) {
        Subrace subrace = getSubRaceById(subRaceId);
        RacialTraits racialTraits = this.racialTraitService.findByName(body.racialTraitName());
        subrace.removeRacialTrait(racialTraits);
        return this.subraceRepository.save(subrace);
    }

    public Subrace addLanguage(UUID subRaceId, LanguageNameDTO body) {
        Subrace subrace = getSubRaceById(subRaceId);
        Languages found = this.languageService.findByName(body.languageName());
        subrace.addLanguage(found);
        return this.subraceRepository.save(subrace);
    }

    public Subrace removeLanguage(UUID subRaceId, LanguageNameDTO body) {
        Subrace subrace = getSubRaceById(subRaceId);
        Languages found = this.languageService.findByName(body.languageName());
        subrace.removeLanguage(found);
        return this.subraceRepository.save(subrace);
    }

    public Subrace findByName(String name) {
        return subraceRepository.findByName(name).orElseThrow(() -> new NotFoundException("Sotto razza con nome: " + name + " non trovato!"));
    }
}
