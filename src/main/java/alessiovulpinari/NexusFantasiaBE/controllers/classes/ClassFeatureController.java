package alessiovulpinari.NexusFantasiaBE.controllers.classes;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Class;
import alessiovulpinari.NexusFantasiaBE.entities.classes.ClassFeature;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.ClassDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.ClassFeatureDTO;
import alessiovulpinari.NexusFantasiaBE.services.classes.ClassFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/classFeatures")
@CrossOrigin(origins = "http://localhost:5173")
public class ClassFeatureController {

    @Autowired
    private ClassFeatureService classFeatureService;

    //TODO da sostituire con la risposta DTO
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    ClassFeature createClassFeature(@RequestBody @Validated ClassFeatureDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return classFeatureService.saveClass(body);
    }

    @GetMapping
    Page<ClassFeature> getAllFeatures(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return classFeatureService.getClassFeatures(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClassFeature getFeatureById(@PathVariable UUID id) {
        return classFeatureService.getClassFeature(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClassFeature(@PathVariable UUID id) {
        classFeatureService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    ClassFeature updateClassFeature(@PathVariable UUID id, @Validated @RequestBody ClassFeatureDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return classFeatureService.findByIdAndUpdate(id, body);
    }

}
