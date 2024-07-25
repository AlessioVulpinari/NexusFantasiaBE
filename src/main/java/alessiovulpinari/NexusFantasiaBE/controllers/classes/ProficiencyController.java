package alessiovulpinari.NexusFantasiaBE.controllers.classes;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Proficiency;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.ProficiencyDTO;
import alessiovulpinari.NexusFantasiaBE.services.classes.ProficiencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/proficiencies")
@CrossOrigin(origins = "http://localhost:5173")
public class ProficiencyController {

    @Autowired
    private ProficiencyService proficiencyService;

    //TODO da sostituire con la risposta DTO
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    Proficiency createProficiency(@RequestBody @Validated ProficiencyDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return proficiencyService.saveProficiency(body);
    }

    @GetMapping
    Page<Proficiency> getAllProficiencies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return proficiencyService.getProficiencies(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Proficiency getProficiencyById(@PathVariable UUID id) {
        return proficiencyService.getProficiencyById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProficiency(@PathVariable UUID id) {
        proficiencyService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Proficiency updateProficiency(@PathVariable UUID id, @Validated @RequestBody ProficiencyDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return proficiencyService.findByIdAndUpdate(id, body);
    }
}
