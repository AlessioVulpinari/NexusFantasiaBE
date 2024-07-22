package alessiovulpinari.NexusFantasiaBE.services.sheets;

import alessiovulpinari.NexusFantasiaBE.entities.sheet.AbilityScore;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.repositories.sheet.AbilityScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbilityScoreService {

    @Autowired
    private AbilityScoreRepository abilityScoreRepository;

    public AbilityScore findByName (String name) {
       return abilityScoreRepository.findByName(name).orElseThrow(()-> new NotFoundException("Caratteristica con questo nome non trovata!"));
    }
 }
