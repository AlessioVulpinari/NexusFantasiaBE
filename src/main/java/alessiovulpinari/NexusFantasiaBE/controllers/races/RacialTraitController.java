package alessiovulpinari.NexusFantasiaBE.controllers.races;

import alessiovulpinari.NexusFantasiaBE.entities.races.RacialTraits;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.races.RacialTraitDTO;
import alessiovulpinari.NexusFantasiaBE.services.races.RacialTraitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/racialTraits")
@CrossOrigin(origins = "http://localhost:5173")
public class RacialTraitController {

    @Autowired
    private RacialTraitService racialTraitService;

    //TODO da sostituire con la risposta DTO
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    RacialTraits createRacialTrait(@RequestBody @Validated RacialTraitDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return racialTraitService.saveRacialTrait(body);
    }

    @GetMapping
    Page<RacialTraits> getAllRacialTraits(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return racialTraitService.getRacialTraits(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RacialTraits getRacialTraitById(@PathVariable UUID id) {
        return racialTraitService.getRacialTraitById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRacialTraits(@PathVariable UUID id) {
        racialTraitService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    RacialTraits updateRacialTrait(@PathVariable UUID id, @Validated @RequestBody RacialTraitDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return racialTraitService.findByIdAndUpdate(id, body);
    }

}
