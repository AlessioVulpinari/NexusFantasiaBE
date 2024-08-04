package alessiovulpinari.NexusFantasiaBE.services.races;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Proficiency;
import alessiovulpinari.NexusFantasiaBE.entities.races.Languages;
import alessiovulpinari.NexusFantasiaBE.entities.races.Race;
import alessiovulpinari.NexusFantasiaBE.entities.races.RacialTraits;
import alessiovulpinari.NexusFantasiaBE.entities.races.Subrace;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.races.*;
import alessiovulpinari.NexusFantasiaBE.repositories.races.RaceRepository;
import alessiovulpinari.NexusFantasiaBE.services.classes.ProficiencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RaceService {

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private RacialTraitService racialTraitService;

    @Autowired
    private LanguageService languageService;

    @Autowired
    private CommonRaceService commonRaceService;

    @Autowired
    private ProficiencyService proficiencyService;

    public Page<Race> getRaces(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return raceRepository.findAll(pageable);
    }

    public Race getRaceById(UUID uuid) {
        return raceRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Nessuna razza con questo id trovata!"));
    }

    public Race saveRace(RaceDTO body) {
        this.raceRepository.findByName(body.name()).ifPresent
                (race -> {throw new BadRequestException("Esiste già una razza con questo nome: " + body.name());});

        Race race = new Race(body.name(), body.description(), body.ageDescription(), body.alignmentDescription(),
                body.sizeDescription(), body.speed());

        return  this.raceRepository.save(race);
    }

    public Race findByIdAndUpdate(UUID raceId, RaceDTO body) {

        Race found = this.getRaceById(raceId);
        found.setName(body.name());
        found.setDescription(body.description());
        found.setAgeDescription(body.ageDescription());
        found.setAlignmentDescription(body.alignmentDescription());
        found.setSizeDescription(body.sizeDescription());
        found.setSpeed(body.speed());

        return this.raceRepository.save(found);
    }

    public void findByIdAndDelete(UUID raceId) {
        Race found = getRaceById(raceId);
        this.raceRepository.delete(found);
    }

    public Race addRacialTrait(UUID raceId, RacialTraitNameDTO racialTraitNameDTO) {
        Race found = this.getRaceById(raceId);
        RacialTraits racialTraits = racialTraitService.findByName(racialTraitNameDTO.racialTraitName());

        found.addRacialTrait(racialTraits);
        return  this.raceRepository.save(found);
    }

    public Race removeRacialTrait(UUID raceId, RacialTraitNameDTO racialTraitNameDTO) {
        Race found = this.getRaceById(raceId);
        RacialTraits racialTraits = racialTraitService.findByName(racialTraitNameDTO.racialTraitName());

        found.removeRacialTrait(racialTraits);
        return  this.raceRepository.save(found);
    }

    public Race addLanguage(UUID raceId, LanguageNameDTO body) {
        Race found = this.getRaceById(raceId);
        Languages languages = languageService.findByName(body.languageName());

        found.addLanguage(languages);
        return this.raceRepository.save(found);
    }

    public Race removeLanguage(UUID raceId, LanguageNameDTO body) {
        Race found = this.getRaceById(raceId);
        Languages languages = languageService.findByName(body.languageName());

        found.removeLanguages(languages);
        return this.raceRepository.save(found);
    }

    public Race addSubRace(UUID raceId, SubRaceNameDTO body) {
        Race found = this.getRaceById(raceId);
        Subrace subrace = commonRaceService.findSubRaceByName(body.subRaceName());

        found.addSubRace(subrace);
        return this.raceRepository.save(found);
    }

    public Race removeSubRace(UUID raceId, SubRaceNameDTO body) {
        Race found = this.getRaceById(raceId);
        Subrace subrace = commonRaceService.findSubRaceByName(body.subRaceName());

        found.removeSubRace(subrace);
        return this.raceRepository.save(found);
    }

    public Race addProficiency(UUID raceId, ProficiencyNameDTO body) {
        Race found = this.getRaceById(raceId);
        Proficiency proficiency = this.proficiencyService.findByProficiencyName(body.proficiencyName());

        found.addProficiency(proficiency);
        return this.raceRepository.save(found);
    }

    public Race removeProficiency(UUID raceId, ProficiencyNameDTO body) {
        Race found = this.getRaceById(raceId);
        Proficiency proficiency = this.proficiencyService.findByProficiencyName(body.proficiencyName());

        found.removeProficiency(proficiency);
        return this.raceRepository.save(found);
    }

    public Race findByName (String name) {
        return commonRaceService.findRaceByName(name);
    }
}
