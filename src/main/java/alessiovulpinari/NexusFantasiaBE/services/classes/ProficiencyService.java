package alessiovulpinari.NexusFantasiaBE.services.classes;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Proficiency;
import alessiovulpinari.NexusFantasiaBE.enums.ProficiencyType;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.ProficiencyDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.classes.ProficiencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProficiencyService {

    @Autowired
    private ProficiencyRepository proficiencyRepository;

    public Page<Proficiency> getProficiencies(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return proficiencyRepository.findAll(pageable);
    }

    public Proficiency getProficiencyById(UUID uuid) {
        return proficiencyRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Competenza con questo id non trovata!"));
    }

    public Proficiency saveProficiency(ProficiencyDTO body) {
        try {
            this.proficiencyRepository.findByNameAndProficiencyType(body.name(), ProficiencyType.valueOf(body.proficiencyType()));
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }

        Proficiency newProficiency = new Proficiency(ProficiencyType.valueOf(body.proficiencyType()) , body.name(), body.description());
        return proficiencyRepository.save(newProficiency);
    }

    public Proficiency findByIdAndUpdate(UUID proficiencyId, ProficiencyDTO body) {
        Proficiency found = getProficiencyById(proficiencyId);

        proficiencyRepository.findByNameAndProficiencyType(body.name(), ProficiencyType.valueOf(body.proficiencyType())).ifPresent(proficiency ->
        {throw new BadRequestException("Esiste già una competenza con questo nome e di questo tipo!");});

        found.setName(body.name());
        found.setDescription(body.description());
        try {found.setProficiencyType(ProficiencyType.valueOf(body.proficiencyType()));}
        catch (Exception e) {throw new BadRequestException(e.getMessage());}

        return proficiencyRepository.save(found);
    }

    public void findByIdAndDelete(UUID proficiencyId) {
        Proficiency found = getProficiencyById(proficiencyId);
        this.proficiencyRepository.delete(found);
    }
}
