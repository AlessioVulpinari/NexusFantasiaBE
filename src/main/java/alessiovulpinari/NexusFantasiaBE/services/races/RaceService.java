package alessiovulpinari.NexusFantasiaBE.services.races;

import alessiovulpinari.NexusFantasiaBE.entities.races.Race;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.repositories.races.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RaceService {

    @Autowired
    private RaceRepository raceRepository;

    public Race findByName (String name) {
        return raceRepository.findByName(name).orElseThrow(()-> new NotFoundException("Caratteristica con questo nome non trovata!"));
    }
}
