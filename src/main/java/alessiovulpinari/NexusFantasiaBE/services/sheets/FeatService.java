package alessiovulpinari.NexusFantasiaBE.services.sheets;

import alessiovulpinari.NexusFantasiaBE.entities.races.Subrace;
import alessiovulpinari.NexusFantasiaBE.entities.sheet.Feat;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.repositories.sheet.FeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeatService {

    @Autowired
    private FeatRepository featRepository;

    public Feat findByName(String name) {
        return featRepository.findByName(name).orElseThrow(() -> new NotFoundException("Talento con nome: " + name + " non trovato!"));
    }
}
