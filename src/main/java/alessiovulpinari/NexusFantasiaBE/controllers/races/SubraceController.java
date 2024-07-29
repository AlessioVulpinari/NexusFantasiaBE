package alessiovulpinari.NexusFantasiaBE.controllers.races;

import alessiovulpinari.NexusFantasiaBE.entities.races.Subrace;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.races.LanguageNameDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.races.RacialTraitNameDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.races.SubRaceDTO;
import alessiovulpinari.NexusFantasiaBE.services.races.SubRaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/subraces")
@CrossOrigin(origins = "http://localhost:5173")
public class SubraceController {

    @Autowired
    private SubRaceService subRaceService;

    //TODO da sostituire con la risposta DTO
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    Subrace createSubrace(@RequestBody @Validated SubRaceDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return subRaceService.saveSubRace(body);
    }

    @GetMapping
    Page<Subrace> getAllSubRace(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return subRaceService.getSubRaces(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Subrace getSubRaceById(@PathVariable UUID id) {
        return subRaceService.getSubRaceById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubRace(@PathVariable UUID id) {
        subRaceService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Subrace updateSubRace(@PathVariable UUID id, @Validated @RequestBody SubRaceDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return subRaceService.findByIdAndUpdate(id, body);
    }

    @PutMapping("/{id}/traits/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Subrace addRacialTrait(@PathVariable UUID id, @Validated @RequestBody RacialTraitNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return subRaceService.addRacialTrait(id, body);
    }

    @PutMapping("/{id}/traits/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Subrace removeRacialTrait(@PathVariable UUID id, @Validated @RequestBody RacialTraitNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return subRaceService.removeRacialTrait(id, body);
    }

    @PutMapping("/{id}/languages/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Subrace addLanguage(@PathVariable UUID id, @Validated @RequestBody LanguageNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return subRaceService.addLanguage(id, body);
    }

    @PutMapping("/{id}/languages/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Subrace removeLanguage(@PathVariable UUID id, @Validated @RequestBody LanguageNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return subRaceService.removeLanguage(id, body);
    }

}
