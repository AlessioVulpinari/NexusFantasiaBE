package alessiovulpinari.NexusFantasiaBE.controllers.equipments;

import alessiovulpinari.NexusFantasiaBE.entities.equipments.Equipment;
import alessiovulpinari.NexusFantasiaBE.entities.equipments.WeaponProperty;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.equipments.EquipmentDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.equipments.WeaponPropertyDTO;
import alessiovulpinari.NexusFantasiaBE.services.equipments.WeaponPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/weaponProperty")
@CrossOrigin(origins = "http://localhost:5173")
public class WeaponPropertyController {

    @Autowired
    private WeaponPropertyService weaponPropertyService;

    //TODO da sostituire con la risposta DTO
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    WeaponProperty createWeaponProperty(@RequestBody @Validated WeaponPropertyDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return weaponPropertyService.saveWeaponProperty(body);
    }

    @GetMapping
    Page<WeaponProperty> getAllWeaponProperties(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return weaponPropertyService.getWeaponProperties(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public WeaponProperty getWeaponPropertyById(@PathVariable UUID id) {
        return weaponPropertyService.getWeaponPropertyById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWeaponProperty(@PathVariable UUID id) {
        weaponPropertyService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    WeaponProperty updateWeaponProperty(@PathVariable UUID id, @Validated @RequestBody WeaponPropertyDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return weaponPropertyService.findByIdAndUpdate(id, body);
    }

}
