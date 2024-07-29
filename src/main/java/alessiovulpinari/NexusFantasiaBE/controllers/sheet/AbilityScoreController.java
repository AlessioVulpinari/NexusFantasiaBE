package alessiovulpinari.NexusFantasiaBE.controllers.sheet;

import alessiovulpinari.NexusFantasiaBE.entities.sheet.AbilityScore;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.sheets.AbilityScoreDTO;
import alessiovulpinari.NexusFantasiaBE.services.sheets.AbilityScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/abilityScore")
@CrossOrigin(origins = "http://localhost:5173")
public class AbilityScoreController {

    @Autowired
    private AbilityScoreService abilityScoreService;

    //TODO da sostituire con la risposta DTO
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public AbilityScore createAbilityScore(@RequestBody @Validated AbilityScoreDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return abilityScoreService.saveAbilityScore(body);
    }

    @GetMapping
    public Page<AbilityScore> getAllAbilityScores(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return abilityScoreService.getAbilitiesScore(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AbilityScore getAbilityScoreById(@PathVariable UUID id) {
        return abilityScoreService.getAbilityScoreById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAbilityScore(@PathVariable UUID id) {
        abilityScoreService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public AbilityScore updateAbilityScore(@PathVariable UUID id, @Validated @RequestBody AbilityScoreDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return abilityScoreService.findByIdAndUpdate(id, body);
    }
}
