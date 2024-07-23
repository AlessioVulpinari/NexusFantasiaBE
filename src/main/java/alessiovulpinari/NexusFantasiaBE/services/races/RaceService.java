package alessiovulpinari.NexusFantasiaBE.services.races;

import alessiovulpinari.NexusFantasiaBE.entities.races.Race;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.races.RaceDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.races.RaceRepository;
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

        Race race = new Race(body.name(), body.ageDescription(), body.alignmentDescription(),
                body.sizeDescription(), body.speed());

        return  this.raceRepository.save(race);
    }

    public Race findByIdAndUpdate(UUID raceId, RaceDTO body) {

        Race found = this.getRaceById(raceId);
        found.setName(body.name());
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

    //TODO SETTARE I TRATTI RAZZIALI, I LINGUAGGI, LE SOTTORAZZE E LE COMPETENZE

    public Race findByName (String name) {
        return raceRepository.findByName(name).orElseThrow(()-> new NotFoundException("Razza con questo nome non trovata!"));
    }
}
