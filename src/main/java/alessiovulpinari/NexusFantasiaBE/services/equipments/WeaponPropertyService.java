package alessiovulpinari.NexusFantasiaBE.services.equipments;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Class;
import alessiovulpinari.NexusFantasiaBE.entities.equipments.WeaponProperty;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.ClassDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.equipments.WeaponPropertyDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.equipments.WeaponPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WeaponPropertyService {

    @Autowired
    private WeaponPropertyRepository weaponPropertyRepository;

    public Page<WeaponProperty> getWeaponProperties(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return weaponPropertyRepository.findAll(pageable);
    }

    public WeaponProperty getWeaponPropertyById(UUID uuid) {
        return weaponPropertyRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Proprietà arma con questo id non trovata!"));
    }

    public WeaponProperty saveWeaponProperty(WeaponPropertyDTO body) {

        this.weaponPropertyRepository.findByName(body.name()).ifPresent
                (property -> {throw new BadRequestException("Esiste già una proprietà con questo nome: " + body.name());});

        WeaponProperty newProperty = new WeaponProperty(body.name(), body.description());

        return weaponPropertyRepository.save(newProperty);
    }

    public WeaponProperty findByIdAndUpdate(UUID propertyId, WeaponPropertyDTO body)
    {
        WeaponProperty found = getWeaponPropertyById(propertyId);

        found.setName(body.name());
        found.setDescription(body.description());

        return weaponPropertyRepository.save(found);
    }

    public void findByIdAndDelete(UUID propertyId) {
        WeaponProperty found = getWeaponPropertyById(propertyId);
        this.weaponPropertyRepository.delete(found);
    }

}
