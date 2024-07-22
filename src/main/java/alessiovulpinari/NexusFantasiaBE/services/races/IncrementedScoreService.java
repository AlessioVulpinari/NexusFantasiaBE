package alessiovulpinari.NexusFantasiaBE.services.races;

import alessiovulpinari.NexusFantasiaBE.entities.magics.Magic;
import alessiovulpinari.NexusFantasiaBE.entities.races.IncrementedScore;
import alessiovulpinari.NexusFantasiaBE.entities.races.Race;
import alessiovulpinari.NexusFantasiaBE.entities.races.Subrace;
import alessiovulpinari.NexusFantasiaBE.entities.sheet.AbilityScore;
import alessiovulpinari.NexusFantasiaBE.entities.sheet.Feat;
import alessiovulpinari.NexusFantasiaBE.enums.MagicSchool;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.magics.MagicDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.races.IncrementedScoreFeatDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.races.IncrementedScoreRaceDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.races.IncrementedScoreSubRaceDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.races.IncrementedScoreRepository;
import alessiovulpinari.NexusFantasiaBE.services.sheets.AbilityScoreService;
import alessiovulpinari.NexusFantasiaBE.services.sheets.FeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IncrementedScoreService {

    @Autowired
    private IncrementedScoreRepository incrementedScoreRepository;

    @Autowired
    private AbilityScoreService abilityScoreService;

    @Autowired
    private RaceService raceService;

    @Autowired
    private SubRaceService subRaceService;

    @Autowired
    private FeatService featService;

    public Page<IncrementedScore> getIncrements(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return incrementedScoreRepository.findAll(pageable);
    }

    public IncrementedScore getIncrementedScoreById(UUID uuid) {
        return incrementedScoreRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Incremento con questo id non trovato!"));
    }

    public IncrementedScore saveRaceIncrementedScore(IncrementedScoreRaceDTO body) {

        AbilityScore abilityScore = abilityScoreService.findByName(body.abilityScoreName());
        Race race = raceService.findByName(body.raceName());
        IncrementedScore incrementedScore = new IncrementedScore(abilityScore, body.incrementValue(), race );

        return incrementedScoreRepository.save(incrementedScore);
    }

    public IncrementedScore saveSubRaceIncrementedScore(IncrementedScoreSubRaceDTO body) {

        AbilityScore abilityScore = abilityScoreService.findByName(body.abilityScoreName());
        Subrace race = subRaceService.findByName(body.subRaceName());
        IncrementedScore incrementedScore = new IncrementedScore(abilityScore, body.incrementValue(), race );

        return incrementedScoreRepository.save(incrementedScore);
    }

    public IncrementedScore saveFeatIncrementedScore(IncrementedScoreFeatDTO body) {
        AbilityScore abilityScore = abilityScoreService.findByName(body.abilityScoreName());
        Feat feat = featService.findByName(body.featName());
        IncrementedScore incrementedScore = new IncrementedScore(abilityScore, body.incrementValue(), feat);

        return incrementedScoreRepository.save(incrementedScore);
    }

    //TODO aggiungere metodo per modificare (PUT)

    public void findByIdAndDelete(UUID incrementedScoreId) {
        IncrementedScore found = getIncrementedScoreById(incrementedScoreId);
        this.incrementedScoreRepository.delete(found);
    }
}
