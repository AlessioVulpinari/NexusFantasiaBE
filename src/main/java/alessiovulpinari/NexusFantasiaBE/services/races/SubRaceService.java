package alessiovulpinari.NexusFantasiaBE.services.races;

import alessiovulpinari.NexusFantasiaBE.entities.races.Subrace;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.repositories.races.SubraceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubRaceService {

    @Autowired
    private SubraceRepository subraceRepository;

    public Subrace findByName(String name) {
        return subraceRepository.findByName(name).orElseThrow(() -> new NotFoundException("Sotto razza con nome: " + name + " non trovato!"));
    }
}
