package alessiovulpinari.NexusFantasiaBE.controllers.classes;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Class;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.ClassDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.equipments.EquipmentNameDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.races.ProficiencyNameDTO;
import alessiovulpinari.NexusFantasiaBE.services.classes.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/classes")
@CrossOrigin(origins = "http://localhost:5173")
public class ClassController {

    @Autowired
    private ClassService classService;

    //TODO da sostituire con la risposta DTO
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    Class createClass(@RequestBody @Validated ClassDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return classService.saveClass(body);
    }

    @GetMapping
    Page<Class> getAllLevels(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return classService.getClasses(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Class getClassById(@PathVariable UUID id) {
        return classService.getClass(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClass(@PathVariable UUID id) {
        classService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Class updateClass(@PathVariable UUID id, @Validated @RequestBody ClassDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return classService.findByIdAndUpdate(id, body);
    }

    @PutMapping("/{id}/proficiencies/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Class addProficiency(@PathVariable UUID id, @Validated @RequestBody ProficiencyNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return classService.addToProficiencyList(id, body);
    }

    @PutMapping("/{id}/proficiencies/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Class removeProficiency(@PathVariable UUID id, @Validated @RequestBody ProficiencyNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return classService.removeToProficiencyList(id, body);
    }

    @PutMapping("/{id}/equipments/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Class addEquipment(@PathVariable UUID id, @Validated @RequestBody EquipmentNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return classService.addToEquipmentList(id, body);
    }

    @PutMapping("/{id}/equipments/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Class removeEquipment(@PathVariable UUID id, @Validated @RequestBody EquipmentNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return classService.removeToEquipmentList(id, body);
    }

}
