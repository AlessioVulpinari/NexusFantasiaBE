package alessiovulpinari.NexusFantasiaBE.services.magics;

import alessiovulpinari.NexusFantasiaBE.entities.magics.MagicComponent;
import alessiovulpinari.NexusFantasiaBE.enums.ComponentSymbol;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.magics.MagicComponentDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.magics.MagicComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MagicComponentService {

    @Autowired
    private MagicComponentRepository magicComponentRepository;

    public Page<MagicComponent> getMagicComponents(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return magicComponentRepository.findAll(pageable);
    }

    public MagicComponent getComponentsById(UUID uuid) {
        return magicComponentRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Componente magico con questo id non trovato!"));
    }

    public MagicComponent saveMagicComponent(MagicComponentDTO body) {
        this.magicComponentRepository.findByName(body.name()).ifPresent
                (aClass -> {throw new BadRequestException("Esiste già un componente magico con questo nome: " + body.name());});

        MagicComponent component = new MagicComponent(body.name(), body.description(), ComponentSymbol.valueOf(body.componentSymbol()));

        return magicComponentRepository.save(component);
    }

    public MagicComponent findByIdAndUpdate(UUID componentId, MagicComponentDTO body)
    {
        MagicComponent found = getComponentsById(componentId);
        found.setName(body.name());
        found.setDescription(body.description());
        found.setComponentSymbol(ComponentSymbol.valueOf(body.componentSymbol()));

        return magicComponentRepository.save(found);
    }

    public void findByIdAndDelete(UUID componentId) {
        MagicComponent found = getComponentsById(componentId);
        this.magicComponentRepository.delete(found);
    }

    public MagicComponent findByComponentName(String name){
        return this.magicComponentRepository.findByName(name).orElseThrow(() -> new NotFoundException("Componente con nome: " + name + " non trovato!"));
    }

}
