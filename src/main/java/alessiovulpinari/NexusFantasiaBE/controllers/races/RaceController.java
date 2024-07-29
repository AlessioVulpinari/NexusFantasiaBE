package alessiovulpinari.NexusFantasiaBE.controllers.races;

import alessiovulpinari.NexusFantasiaBE.entities.races.Race;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.races.*;
import alessiovulpinari.NexusFantasiaBE.services.races.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/races")
@CrossOrigin(origins = "http://localhost:5173")
public class RaceController {

    @Autowired
    private RaceService raceService;

    //TODO da sostituire con la risposta DTO
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    Race createRace(@RequestBody @Validated RaceDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return raceService.saveRace(body);
    }

    @GetMapping
    Page<Race> getAllRaces(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return raceService.getRaces(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Race getRaceById(@PathVariable UUID id) {
        return raceService.getRaceById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRace(@PathVariable UUID id) {
        raceService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Race updateRace(@PathVariable UUID id, @Validated @RequestBody RaceDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return raceService.findByIdAndUpdate(id, body);
    }

    @PutMapping("/{id}/traits/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Race addRacialTraitRace(@PathVariable UUID id, @Validated @RequestBody RacialTraitNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return raceService.addRacialTrait(id, body);
    }

    @PutMapping("/{id}/traits/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Race removeRacialTraitRace(@PathVariable UUID id, @Validated @RequestBody RacialTraitNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return raceService.removeRacialTrait(id, body);
    }

    @PutMapping("/{id}/languages/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Race addLanguage(@PathVariable UUID id, @Validated @RequestBody LanguageNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return raceService.addLanguage(id, body);
    }

    @PutMapping("/{id}/languages/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Race removeLanguage(@PathVariable UUID id, @Validated @RequestBody LanguageNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return raceService.removeLanguage(id, body);
    }

    @PutMapping("/{id}/subraces/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Race addSubrace(@PathVariable UUID id, @Validated @RequestBody SubRaceNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return raceService.addSubRace(id, body);
    }

    @PutMapping("/{id}/subraces/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Race removeSubrace(@PathVariable UUID id, @Validated @RequestBody SubRaceNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return raceService.removeSubRace(id, body);
    }

    @PutMapping("/{id}/proficiencies/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Race addProficiency(@PathVariable UUID id, @Validated @RequestBody ProficiencyNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return raceService.addProficiency(id, body);
    }

    @PutMapping("/{id}/proficiencies/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Race removeProficiency(@PathVariable UUID id, @Validated @RequestBody ProficiencyNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return raceService.removeProficiency(id, body);
    }

}
