package alessiovulpinari.NexusFantasiaBE.services.equipments;

import alessiovulpinari.NexusFantasiaBE.entities.equipments.ArmorAndShield;
import alessiovulpinari.NexusFantasiaBE.entities.equipments.Equipment;
import alessiovulpinari.NexusFantasiaBE.enums.ProtectionType;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.equipments.ArmorAndShieldDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.equipments.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ArmorAndShieldService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EquipmentService equipmentService;

    public ArmorAndShield saveEquipment(ArmorAndShieldDTO body) {

        this.equipmentRepository.findByName(body.name()).ifPresent
                (equipment -> {throw new BadRequestException("Esiste già un oggetto con questo nome: " + body.name());});

        ArmorAndShield newEquipment = new ArmorAndShield(body.name(), body.description(), body.weight(), body.cost(),
                ProtectionType.valueOf(body.protectionType()) , body.armorClass(), body.strengthRequired() == null ? 0 : body.strengthRequired(),
                body.stealthDisadvantage());

        return this.equipmentRepository.save(newEquipment);
    }

    public ArmorAndShield findByIdAndUpdate(UUID equipmentId, ArmorAndShieldDTO body) {

        this.equipmentRepository.findByName(body.name()).ifPresent
                (equipment -> {throw new BadRequestException("Esiste già un oggetto con questo nome: " + body.name());});

        Equipment found = this.equipmentService.getEquipmentById(equipmentId);

        if (!(found instanceof ArmorAndShield armorAndShield)) throw new BadRequestException("L'id inserito non è quello di un armatura o di uno scudo!");

        armorAndShield.setName(body.name());
        armorAndShield.setDescription(body.description());
        armorAndShield.setWeight(body.weight());
        armorAndShield.setCost(body.cost());
        armorAndShield.setProtectionType(ProtectionType.valueOf(body.protectionType()));
        armorAndShield.setArmorClass(body.armorClass());
        armorAndShield.setStrengthRequired(body.strengthRequired() == null ? 0 : body.strengthRequired());
        armorAndShield.setStealthDisadvantage(body.stealthDisadvantage());

        return this.equipmentRepository.save(armorAndShield);
    }
}
