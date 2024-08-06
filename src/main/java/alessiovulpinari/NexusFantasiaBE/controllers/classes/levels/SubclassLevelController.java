package alessiovulpinari.NexusFantasiaBE.controllers.classes.levels;

import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.SubclassLevel;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.ClassFeatureNameDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.levels.SubClassLevelDTO;
import alessiovulpinari.NexusFantasiaBE.services.classes.levels.SubClassLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/subClassLevels")
@CrossOrigin(origins = "http://localhost:5173")
public class SubclassLevelController {

    @Autowired
    private SubClassLevelService subClassLevelService;

    //TODO da sostituire con la risposta DTO
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    SubclassLevel createLevel(@RequestBody @Validated SubClassLevelDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return subClassLevelService.saveClassLevel(body);
    }

    @GetMapping
    Page<SubclassLevel> getAllLevels(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return subClassLevelService.getLevels(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubclassLevel getLevelById(@PathVariable UUID id) {
        return subClassLevelService.getLevelById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevel(@PathVariable UUID id) {
        subClassLevelService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    SubclassLevel updateLevel(@PathVariable UUID id, @Validated @RequestBody SubClassLevelDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return subClassLevelService.findByIdAndUpdate(id, body);
    }

//    @PatchMapping("/{id}/features/add")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @ResponseStatus(HttpStatus.OK)
//    SubclassLevel addFeature(@PathVariable UUID id, @Validated @RequestBody ClassFeatureNameDTO body, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            throw new BadRequestException(bindingResult.getAllErrors());
//        }
//        return subClassLevelService.setClassFeature(id, body);
//    }

}
