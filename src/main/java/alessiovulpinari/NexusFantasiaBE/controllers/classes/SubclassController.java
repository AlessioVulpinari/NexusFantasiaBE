package alessiovulpinari.NexusFantasiaBE.controllers.classes;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Subclass;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.SubclassDTO;
import alessiovulpinari.NexusFantasiaBE.services.classes.SubclassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/subclasses")
@CrossOrigin(origins = "http://localhost:5173")
public class SubclassController {

    @Autowired
    private SubclassService subclassService;

    //TODO da sostituire con la risposta DTO
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    Subclass createSubClass(@RequestBody @Validated SubclassDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return subclassService.saveClass(body);
    }

    @GetMapping
    Page<Subclass> getAllSubclasses(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return subclassService.getSubclass(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Subclass getSubClassById(@PathVariable UUID id) {
        return subclassService.getSubClass(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubClass(@PathVariable UUID id) {
        subclassService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Subclass updateSubClass(@PathVariable UUID id, @Validated @RequestBody SubclassDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return subclassService.findByIdAndUpdate(id, body);
    }
}
