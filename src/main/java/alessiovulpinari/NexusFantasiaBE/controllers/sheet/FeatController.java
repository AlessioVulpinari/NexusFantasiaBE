package alessiovulpinari.NexusFantasiaBE.controllers.sheet;

import alessiovulpinari.NexusFantasiaBE.entities.sheet.Feat;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.sheets.FeatDTO;
import alessiovulpinari.NexusFantasiaBE.services.sheets.FeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/feats")
@CrossOrigin(origins = "http://localhost:5173")
public class FeatController {

    @Autowired
    private FeatService featService;

    //TODO da sostituire con la risposta DTO
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Feat createFeat(@RequestBody @Validated FeatDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return featService.saveFeat(body);
    }

    @GetMapping
    public Page<Feat> getAllFeats(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return featService.getFeats(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Feat getFeatById(@PathVariable UUID id) {
        return featService.getFeatById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFeat(@PathVariable UUID id) {
        featService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Feat updateFeat(@PathVariable UUID id, @Validated @RequestBody FeatDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return featService.findByIdAndUpdate(id, body);
    }
}
