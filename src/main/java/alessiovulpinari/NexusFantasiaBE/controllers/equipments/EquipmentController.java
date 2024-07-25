package alessiovulpinari.NexusFantasiaBE.controllers.equipments;

import alessiovulpinari.NexusFantasiaBE.entities.equipments.*;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.equipments.*;
import alessiovulpinari.NexusFantasiaBE.services.equipments.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/equipments")
@CrossOrigin(origins = "http://localhost:5173")
public class EquipmentController {

    @Autowired
    private ArmorAndShieldService armorAndShieldService;

    @Autowired
    private ContainerService containerService;

    @Autowired
    private EquipmentPackService equipmentPackService;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private WeaponService weaponService;

    //TODO da sostituire con la risposta DTO
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    Equipment createEquipment(@RequestBody @Validated EquipmentDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return equipmentService.saveEquipment(body);
    }

    //TODO da sostituire con la risposta DTO
    @PostMapping("/armorsAndShields")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    ArmorAndShield createArmor(@RequestBody @Validated ArmorAndShieldDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return armorAndShieldService.saveEquipment(body);
    }

    //TODO da sostituire con la risposta DTO
    @PostMapping("/containers")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    Container createContainer(@RequestBody @Validated ContainerDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return containerService.saveEquipment(body);
    }

    //TODO da sostituire con la risposta DTO
    @PostMapping("/packs")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    EquipmentPack createEquipmentPack(@RequestBody @Validated EquipmentListDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return equipmentPackService.saveEquipment(body);
    }

    //TODO da sostituire con la risposta DTO
    @PostMapping("/weapons")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    Weapon createWeapon(@RequestBody @Validated WeaponDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return weaponService.saveEquipment(body);
    }

    @GetMapping
    Page<Equipment> getAllEquipments(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return equipmentService.getEquipments(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Equipment getEquipmentById(@PathVariable UUID id) {
        return equipmentService.getEquipmentById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEquipment(@PathVariable UUID id) {
        equipmentService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Equipment updateClass(@PathVariable UUID id, @Validated @RequestBody EquipmentDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return equipmentService.findByIdAndUpdate(id, body);
    }

    @PutMapping("/armorsAndShields/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    ArmorAndShield updateArmor(@PathVariable UUID id, @Validated @RequestBody ArmorAndShieldDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return armorAndShieldService.findByIdAndUpdate(id, body);
    }

    @PutMapping("/containers/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Container updateContainer(@PathVariable UUID id, @Validated @RequestBody ContainerDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return containerService.findByIdAndUpdate(id, body);
    }

    @PutMapping("/packs/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    EquipmentPack updatePack(@PathVariable UUID id, @Validated @RequestBody EquipmentListDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return equipmentPackService.findByIdAndUpdate(id, body);
    }

    @PutMapping("/packs/{id}/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    EquipmentPack addEquipmentToPack(@PathVariable UUID id, @Validated @RequestBody EquipmentNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return equipmentPackService.addEquipment(id, body);
    }

    @PutMapping("/packs/{id}/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    EquipmentPack removeEquipmentToPack(@PathVariable UUID id, @Validated @RequestBody EquipmentNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return equipmentPackService.removeEquipment(id, body);
    }

    @PutMapping("/weapons/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Weapon updateWeapon(@PathVariable UUID id, @Validated @RequestBody WeaponDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return weaponService.findByIdAndUpdate(id, body);
    }
}
