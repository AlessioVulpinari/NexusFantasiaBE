package alessiovulpinari.NexusFantasiaBE.services.races;

import alessiovulpinari.NexusFantasiaBE.entities.races.Languages;
import alessiovulpinari.NexusFantasiaBE.entities.races.Race;
import alessiovulpinari.NexusFantasiaBE.entities.races.RacialTraits;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.races.LanguageDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.races.RacialTraitDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.races.RacialTraitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RacialTraitService {

    @Autowired
    private RacialTraitRepository racialTraitRepository;

    public Page<RacialTraits> getRacialTraits(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return racialTraitRepository.findAll(pageable);
    }

    public RacialTraits getRacialTraitById(UUID uuid) {
        return racialTraitRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Nessun tratto razziale con questo id trovato!"));
    }

    public RacialTraits saveRacialTrait(RacialTraitDTO body) {
        this.racialTraitRepository.findByName(body.name()).ifPresent
                (aClass -> {throw new BadRequestException("Esiste già un tratto razziale con questo nome: " + body.name());});

       RacialTraits trait = new RacialTraits(body.name(), body.description());

      return this.racialTraitRepository.save(trait);
    }

    public RacialTraits findByIdAndUpdate(UUID racialTraitId, RacialTraitDTO body) {

        RacialTraits found = this.getRacialTraitById(racialTraitId);
        found.setName(body.name());
        found.setDescription(body.description());

        return this.racialTraitRepository.save(found);
    }

    public void findByIdAndDelete(UUID racialTraitId) {
        RacialTraits found = getRacialTraitById(racialTraitId);
        this.racialTraitRepository.delete(found);
    }

}
