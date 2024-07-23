package alessiovulpinari.NexusFantasiaBE.services.sheets;

import alessiovulpinari.NexusFantasiaBE.entities.sheet.AbilityScore;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.sheets.AbilityScoreDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.sheet.AbilityScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AbilityScoreService {

    @Autowired
    private AbilityScoreRepository abilityScoreRepository;

    public Page<AbilityScore> getAbilitiesScore(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return abilityScoreRepository.findAll(pageable);
    }

    public AbilityScore getAbilityScoreById(UUID uuid) {
        return abilityScoreRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Nessun punteggio caratteristica con questo id trovato!"));
    }

    public AbilityScore saveAbilityScore(AbilityScoreDTO body) {
        this.abilityScoreRepository.findByName(body.name()).ifPresent
                (abilityScore -> {throw new BadRequestException("Esiste già un punteggio caratteristica con questo nome: " + body.name());});

        AbilityScore abilityScore = new AbilityScore(body.name(), body.description());

        return this.abilityScoreRepository.save(abilityScore);
    }

    public AbilityScore findByIdAndUpdate(UUID abilityScoreId, AbilityScoreDTO body) {

        AbilityScore found = this.getAbilityScoreById(abilityScoreId);
        found.setName(body.name());
        found.setDescription(body.description());

        return this.abilityScoreRepository.save(found);
    }

    public void findByIdAndDelete(UUID abilityScoreId) {
        AbilityScore found = getAbilityScoreById(abilityScoreId);
        this.abilityScoreRepository.delete(found);
    }

    public AbilityScore findByName (String name) {
       return abilityScoreRepository.findByName(name).orElseThrow(()-> new NotFoundException("Caratteristica con questo nome non trovata!"));
    }
 }
