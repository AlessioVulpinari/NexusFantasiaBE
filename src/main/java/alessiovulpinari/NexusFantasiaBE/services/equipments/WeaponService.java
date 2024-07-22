package alessiovulpinari.NexusFantasiaBE.services.equipments;

import alessiovulpinari.NexusFantasiaBE.entities.equipments.Container;
import alessiovulpinari.NexusFantasiaBE.entities.equipments.Equipment;
import alessiovulpinari.NexusFantasiaBE.entities.equipments.Weapon;
import alessiovulpinari.NexusFantasiaBE.enums.DamageType;
import alessiovulpinari.NexusFantasiaBE.enums.WeaponSubtype;
import alessiovulpinari.NexusFantasiaBE.enums.WeaponType;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.equipments.ContainerDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.equipments.WeaponDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.equipments.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WeaponService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EquipmentService equipmentService;

    public Weapon saveEquipment(WeaponDTO body) {

        this.equipmentRepository.findByName(body.name()).ifPresent
                (equipment -> {throw new BadRequestException("Esiste già un oggetto con questo nome: " + body.name());});

        Weapon weapon = new Weapon(body.name(), body.description(), body.weight(), body.cost(),
                body.numberOfDamageDice(), body.damageDice(), WeaponType.valueOf(body.weaponType()),
                WeaponSubtype.valueOf(body.weaponSubtype()) , DamageType.valueOf(body.damageType()));

        return this.equipmentRepository.save(weapon);
    }

    public Weapon findByIdAndUpdate(UUID equipmentId, WeaponDTO body) {

        this.equipmentRepository.findByName(body.name()).ifPresent
                (equipment -> {throw new BadRequestException("Esiste già un oggetto con questo nome: " + body.name());});

        Equipment found = this.equipmentService.getEquipmentById(equipmentId);

        if (!(found instanceof Weapon weapon)) throw new BadRequestException("L'id inserito non è quello di un'arma!");

        weapon.setName(body.name());
        weapon.setDescription(body.description());
        weapon.setWeight(body.weight());
        weapon.setCost(body.cost());
        weapon.setNumberOfDamageDice(body.numberOfDamageDice());
        weapon.setDamageDice(body.damageDice());
        weapon.setWeaponType(WeaponType.valueOf(body.weaponType()));
        weapon.setWeaponSubtype(WeaponSubtype.valueOf(body.weaponSubtype()));
        weapon.setDamageType(DamageType.valueOf(body.damageType()));

        return this.equipmentRepository.save(weapon);
    }
}
