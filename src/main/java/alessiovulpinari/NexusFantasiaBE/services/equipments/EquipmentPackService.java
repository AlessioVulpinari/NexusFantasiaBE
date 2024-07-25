package alessiovulpinari.NexusFantasiaBE.services.equipments;

import alessiovulpinari.NexusFantasiaBE.entities.equipments.Container;
import alessiovulpinari.NexusFantasiaBE.entities.equipments.Equipment;
import alessiovulpinari.NexusFantasiaBE.entities.equipments.EquipmentPack;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.equipments.ContainerDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.equipments.EquipmentListDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.equipments.EquipmentNameDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.equipments.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EquipmentPackService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EquipmentService equipmentService;

    public EquipmentPack saveEquipment(EquipmentListDTO body) {

        if (body.equipmentList().isEmpty()) throw new BadRequestException("La lista di oggetti non deve essere vuota!");

        this.equipmentRepository.findByName(body.name()).ifPresent
                (equipment -> {throw new BadRequestException("Esiste già un oggetto con questo nome: " + body.name());});

        EquipmentPack newEquipment = new EquipmentPack(body.name(), body.description(), body.weight(), body.cost());

        newEquipment.setEquipmentList(body.equipmentList());

        return this.equipmentRepository.save(newEquipment);
    }

    public EquipmentPack findByIdAndUpdate(UUID equipmentId, EquipmentListDTO body) {

        this.equipmentRepository.findByName(body.name()).ifPresent
                (equipment -> {throw new BadRequestException("Esiste già un oggetto con questo nome: " + body.name());});

        Equipment found = this.equipmentService.getEquipmentById(equipmentId);

        if (!(found instanceof EquipmentPack equipmentPack)) throw new BadRequestException("L'id inserito non è quello di un contenitore!");

        equipmentPack.setName(body.name());
        equipmentPack.setDescription(body.description());
        equipmentPack.setWeight(body.weight());
        equipmentPack.setCost(body.cost());
        equipmentPack.setEquipmentList(body.equipmentList());

        return this.equipmentRepository.save(equipmentPack);
    }

    public EquipmentPack addEquipment(UUID equipmentPackId, EquipmentNameDTO body) {
       Equipment found = equipmentService.getEquipmentById(equipmentPackId);

        if (!(found instanceof EquipmentPack equipmentPack)) throw new BadRequestException("L'id inserito non è quello di un pacchetto iniziale!");
        Equipment equipment = equipmentService.findByName(body.name());
        equipmentPack.addEquipToList(equipment);
        return this.equipmentRepository.save(equipmentPack);
    }

    public EquipmentPack removeEquipment(UUID equipmentPackId, EquipmentNameDTO body) {
        Equipment found = equipmentService.getEquipmentById(equipmentPackId);

        if (!(found instanceof EquipmentPack equipmentPack)) throw new BadRequestException("L'id inserito non è quello di un pacchetto iniziale!");
        Equipment equipment = equipmentService.findByName(body.name());
        equipmentPack.removeEquipToList(equipment);
        return this.equipmentRepository.save(equipmentPack);
    }
}
