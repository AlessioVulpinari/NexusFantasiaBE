package alessiovulpinari.NexusFantasiaBE.services.races;

import alessiovulpinari.NexusFantasiaBE.entities.races.Race;
import alessiovulpinari.NexusFantasiaBE.entities.races.Subrace;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.repositories.races.RaceRepository;
import alessiovulpinari.NexusFantasiaBE.repositories.races.SubraceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonRaceService {

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private SubraceRepository subRaceRepository;

    public Race findRaceByName(String name) {
        return raceRepository.findByName(name).orElseThrow(() -> new NotFoundException("Razza non trovata!"));
    }

    public Subrace findSubRaceByName(String name) {
        return subRaceRepository.findByName(name).orElseThrow(() -> new NotFoundException("Sotto razza non trovata!"));
    }
}

