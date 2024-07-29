package alessiovulpinari.NexusFantasiaBE.controllers.races;

import alessiovulpinari.NexusFantasiaBE.entities.races.IncrementedScore;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.races.IncrementedScoreFeatDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.races.IncrementedScoreRaceDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.races.IncrementedScoreSubRaceDTO;
import alessiovulpinari.NexusFantasiaBE.services.races.IncrementedScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/incrementedScore")
@CrossOrigin(origins = "http://localhost:5173")
public class IncrementedScoreController {

    @Autowired
    private IncrementedScoreService incrementedScoreService;

    //TODO da sostituire con la risposta DTO
    @PostMapping("/race")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    IncrementedScore createRaceIncrementedScore(@RequestBody @Validated IncrementedScoreRaceDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return incrementedScoreService.saveRaceIncrementedScore(body);
    }

    //TODO da sostituire con la risposta DTO
    @PostMapping("/subrace")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    IncrementedScore createSubRaceIncrementedScore(@RequestBody @Validated IncrementedScoreSubRaceDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return incrementedScoreService.saveSubRaceIncrementedScore(body);
    }

    //TODO da sostituire con la risposta DTO
    @PostMapping("/feat")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    IncrementedScore createFeatIncrementedScore(@RequestBody @Validated IncrementedScoreFeatDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return incrementedScoreService.saveFeatIncrementedScore(body);
    }

    @GetMapping
    Page<IncrementedScore> getAllIncrementedScores(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return incrementedScoreService.getIncrements(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public IncrementedScore getIncrementedScoreById(@PathVariable UUID id) {
        return incrementedScoreService.getIncrementedScoreById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIncrementedScore(@PathVariable UUID id) {
        incrementedScoreService.findByIdAndDelete(id);
    }

    @PutMapping("/race/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    IncrementedScore updateRaceIncrementedScore(@PathVariable UUID id, @Validated @RequestBody IncrementedScoreRaceDTO  body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return incrementedScoreService.findByIdAndUpdateRace(id, body);
    }

    @PutMapping("/subrace/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    IncrementedScore updateSubRaceIncrementedScore(@PathVariable UUID id, @Validated @RequestBody IncrementedScoreSubRaceDTO  body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return incrementedScoreService.findByIdAndUpdateSubRace(id, body);
    }

    @PutMapping("/feat/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    IncrementedScore updateFeatIncrementedScore(@PathVariable UUID id, @Validated @RequestBody IncrementedScoreFeatDTO  body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return incrementedScoreService.findByIdAndUpdateFeat(id, body);
    }


}
