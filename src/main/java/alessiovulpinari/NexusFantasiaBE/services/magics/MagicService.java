package alessiovulpinari.NexusFantasiaBE.services.magics;

import alessiovulpinari.NexusFantasiaBE.entities.magics.Magic;
import alessiovulpinari.NexusFantasiaBE.enums.MagicSchool;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.magics.MagicDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.magics.MagicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MagicService {

    @Autowired
    private MagicRepository magicRepository;

    public Page<Magic> getMagics(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return magicRepository.findAll(pageable);
    }

    public Magic getMagicById(UUID uuid) {
        return magicRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Magia con questo id non trovato!"));
    }

    public Magic saveMagic(MagicDTO body) {
        this.magicRepository.findByName(body.name()).ifPresent
                (aClass -> {throw new BadRequestException("Esiste già una magia con questo nome: " + body.name());});

        Magic magic = new Magic(body.name(), body.description(), body.level(), MagicSchool.valueOf(body.magicSchool()),
                body.castingTime(), body.range(), body.duration(), body.atHigherLevels(), body.isRitual());

        return magicRepository.save(magic);
    }

    public Magic findByIdAndUpdate(UUID magicId, MagicDTO body)
    {
        Magic found = getMagicById(magicId);
        found.setName(body.name());
        found.setDescription(body.description());
        found.setLevel(body.level());
        found.setMagicSchool(MagicSchool.valueOf(body.magicSchool()));
        found.setCastingTime(body.castingTime());
        found.setRange(body.range());
        found.setDuration(body.duration());
        found.setAtHigherLevels(body.atHigherLevels());
        found.setIsRitual(body.isRitual());

        return magicRepository.save(found);
    }

    public void findByIdAndDelete(UUID magicId) {
        Magic found = getMagicById(magicId);
        this.magicRepository.delete(found);
    }

    public Magic findByComponentName(String name){
        return this.magicRepository.findByName(name).orElseThrow(() -> new NotFoundException("Magia con nome: " + name + " non trovata!"));
    }

    //TODO AGGIUNGERE COMPONENTI MAGICI, AGGIUNGERE CLASSI, AGGIUNGERE SOTTOCLASSI

}
