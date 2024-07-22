package alessiovulpinari.NexusFantasiaBE.services.equipments;

import alessiovulpinari.NexusFantasiaBE.entities.equipments.Container;
import alessiovulpinari.NexusFantasiaBE.entities.equipments.Equipment;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.equipments.ContainerDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.equipments.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContainerService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EquipmentService equipmentService;

    public Container saveEquipment(ContainerDTO body) {

        this.equipmentRepository.findByName(body.name()).ifPresent
                (equipment -> {throw new BadRequestException("Esiste già un oggetto con questo nome: " + body.name());});

        Container container = new Container(body.name(), body.description(), body.weight(), body.cost(), body.capacity());
        return this.equipmentRepository.save(container);
    }

    public Container findByIdAndUpdate(UUID equipmentId, ContainerDTO body) {

        this.equipmentRepository.findByName(body.name()).ifPresent
                (equipment -> {throw new BadRequestException("Esiste già un oggetto con questo nome: " + body.name());});

        Equipment found = this.equipmentService.getEquipmentById(equipmentId);

        if (!(found instanceof Container container)) throw new BadRequestException("L'id inserito non è quello di un contenitore!");

        container.setName(body.name());
        container.setDescription(body.description());
        container.setWeight(body.weight());
        container.setCost(body.cost());
        container.setCapacity(body.capacity());

        return this.equipmentRepository.save(container);
    }


}
