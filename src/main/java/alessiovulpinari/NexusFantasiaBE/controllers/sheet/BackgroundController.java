package alessiovulpinari.NexusFantasiaBE.controllers.sheet;

import alessiovulpinari.NexusFantasiaBE.entities.sheet.Background;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.equipments.EquipmentNameDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.races.ProficiencyNameDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.sheets.BackgroundDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.sheets.BackgroundFeatureNameDTO;
import alessiovulpinari.NexusFantasiaBE.services.sheets.BackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/backgrounds")
@CrossOrigin(origins = "http://localhost:5173")
public class BackgroundController {

    @Autowired
    private BackgroundService backgroundService;

    //TODO da sostituire con la risposta DTO
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Background createBackground(@RequestBody @Validated BackgroundDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return backgroundService.saveBackground(body);
    }

    @GetMapping
    public Page<Background> getAllBackgrounds(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return backgroundService.getBackgrounds(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Background getBackgroundById(@PathVariable UUID id) {
        return backgroundService.getBackgroundById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBackground(@PathVariable UUID id) {
        backgroundService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Background updateBackground(@PathVariable UUID id, @Validated @RequestBody BackgroundDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return backgroundService.findByIdAndUpdate(id, body);
    }

    @PutMapping("/{id}/proficiencies/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Background addProficiency(@PathVariable UUID id, @Validated @RequestBody ProficiencyNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return backgroundService.addProficiency(id, body);
    }

    @PutMapping("/{id}/proficiencies/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Background removeProficiency(@PathVariable UUID id, @Validated @RequestBody ProficiencyNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return backgroundService.removeProficiency(id, body);
    }

    @PutMapping("/{id}/equipments/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Background addEquipment(@PathVariable UUID id, @Validated @RequestBody EquipmentNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return backgroundService.addEquipment(id, body);
    }

    @PutMapping("/{id}/equipments/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Background removeEquipment(@PathVariable UUID id, @Validated @RequestBody EquipmentNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return backgroundService.removeEquipment(id, body);
    }

    @PutMapping("/{id}/features/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Background addFeature(@PathVariable UUID id, @Validated @RequestBody BackgroundFeatureNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return backgroundService.addBackgroundFeatures(id, body);
    }

    @PutMapping("/{id}/features/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Background removeFeature(@PathVariable UUID id, @Validated @RequestBody BackgroundFeatureNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return backgroundService.removeBackgroundFeatures(id, body);
    }
}
