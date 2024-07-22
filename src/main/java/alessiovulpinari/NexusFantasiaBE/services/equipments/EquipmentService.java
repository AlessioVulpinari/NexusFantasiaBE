package alessiovulpinari.NexusFantasiaBE.services.equipments;

import alessiovulpinari.NexusFantasiaBE.entities.equipments.Equipment;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.equipments.EquipmentDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.equipments.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public Page<Equipment> getEquipments(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return equipmentRepository.findAll(pageable);
    }

    public Equipment getEquipmentById(UUID uuid) {
        return equipmentRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Oggetto con questo id non trovata!"));
    }

    public Equipment saveEquipment(EquipmentDTO body) {
        this.equipmentRepository.findByName(body.name()).ifPresent
                (equipment -> {throw new BadRequestException("Esiste già un oggetto con questo nome: " + body.name());});

        Equipment newEquip = new Equipment(body.name(), body.description(), body.weight(), body.cost());

        return equipmentRepository.save(newEquip);
    }

    public Equipment findByIdAndUpdate(UUID equipId, EquipmentDTO body)
    {
        Equipment found = getEquipmentById(equipId);
        found.setName(body.name());
        found.setDescription(body.description());
        found.setCost(body.cost());
        found.setWeight(body.weight());

        return equipmentRepository.save(found);
    }

    public void findByIdAndDelete(UUID equipId) {
        Equipment found = getEquipmentById(equipId);
        this.equipmentRepository.delete(found);
    }

    public Equipment findByName(String name) {
        return this.equipmentRepository.findByName(name).orElseThrow(() -> new  NotFoundException("Oggetto con nome: " + name + " non trovato!"));
    }
}
