package alessiovulpinari.NexusFantasiaBE.services.races;

import alessiovulpinari.NexusFantasiaBE.entities.races.IncrementedScore;
import alessiovulpinari.NexusFantasiaBE.entities.races.Race;
import alessiovulpinari.NexusFantasiaBE.entities.races.Subrace;
import alessiovulpinari.NexusFantasiaBE.entities.sheet.AbilityScore;
import alessiovulpinari.NexusFantasiaBE.entities.sheet.Feat;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
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
    private CommonRaceService commonRaceService;

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
        Race race = commonRaceService.findRaceByName(body.raceName());
        IncrementedScore incrementedScore = new IncrementedScore(abilityScore, body.incrementValue(), race );

        return incrementedScoreRepository.save(incrementedScore);
    }

    public IncrementedScore saveSubRaceIncrementedScore(IncrementedScoreSubRaceDTO body) {

        AbilityScore abilityScore = abilityScoreService.findByName(body.abilityScoreName());
        Subrace race = commonRaceService.findSubRaceByName(body.subRaceName());
        IncrementedScore incrementedScore = new IncrementedScore(abilityScore, body.incrementValue(), race );

        return incrementedScoreRepository.save(incrementedScore);
    }

    public IncrementedScore saveFeatIncrementedScore(IncrementedScoreFeatDTO body) {
        AbilityScore abilityScore = abilityScoreService.findByName(body.abilityScoreName());
        Feat feat = featService.findByName(body.featName());
        IncrementedScore incrementedScore = new IncrementedScore(abilityScore, body.incrementValue(), feat);

        return incrementedScoreRepository.save(incrementedScore);
    }

    public IncrementedScore findByIdAndUpdateRace(UUID incrementedScoreId, IncrementedScoreRaceDTO body) {

        IncrementedScore found = getIncrementedScoreById(incrementedScoreId);
        AbilityScore abilityScore = abilityScoreService.findByName(body.abilityScoreName());
        Race race = commonRaceService.findRaceByName(body.raceName());
        found.setAbilityScore(abilityScore);
        found.setIncrementValue(body.incrementValue());
        found.setRace(race);
        found.setFeat(null);
        found.setSubrace(null);

        return incrementedScoreRepository.save(found);
    }

    public IncrementedScore findByIdAndUpdateSubRace(UUID incrementedScoreId, IncrementedScoreSubRaceDTO body) {

        IncrementedScore found = getIncrementedScoreById(incrementedScoreId);
        AbilityScore abilityScore = abilityScoreService.findByName(body.abilityScoreName());
        Subrace race = commonRaceService.findSubRaceByName(body.subRaceName());
        found.setAbilityScore(abilityScore);
        found.setIncrementValue(body.incrementValue());
        found.setSubrace(race);
        found.setFeat(null);
        found.setRace(null);

        return incrementedScoreRepository.save(found);
    }

    public IncrementedScore findByIdAndUpdateFeat(UUID incrementedScoreId, IncrementedScoreFeatDTO body) {

        IncrementedScore found = getIncrementedScoreById(incrementedScoreId);
        AbilityScore abilityScore = abilityScoreService.findByName(body.abilityScoreName());
        Feat feat = featService.findByName(body.featName());
        found.setAbilityScore(abilityScore);
        found.setIncrementValue(body.incrementValue());
        found.setSubrace(null);
        found.setFeat(feat);
        found.setRace(null);

        return incrementedScoreRepository.save(found);
    }

    public void findByIdAndDelete(UUID incrementedScoreId) {
        IncrementedScore found = getIncrementedScoreById(incrementedScoreId);
        this.incrementedScoreRepository.delete(found);
    }
}
