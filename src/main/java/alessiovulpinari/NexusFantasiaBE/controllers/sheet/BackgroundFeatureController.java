package alessiovulpinari.NexusFantasiaBE.controllers.sheet;

import alessiovulpinari.NexusFantasiaBE.entities.sheet.BackgroundFeature;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.sheets.BackgroundFeatureDTO;
import alessiovulpinari.NexusFantasiaBE.services.sheets.BackgroundFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/backgroundFeatures")
@CrossOrigin(origins = "http://localhost:5173")
public class BackgroundFeatureController {

    @Autowired
    private BackgroundFeatureService backgroundFeatureService;

    //TODO da sostituire con la risposta DTO
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public BackgroundFeature createBackgroundFeature(@RequestBody @Validated BackgroundFeatureDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return backgroundFeatureService.saveBackgroundFeature(body);
    }

    @GetMapping
    public Page<BackgroundFeature> getAllBackgroundsFeature(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return backgroundFeatureService.getBackgroundFeatures(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BackgroundFeature getBackgroundFeatureById(@PathVariable UUID id) {
        return backgroundFeatureService.getBackgroundFeatureById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBackgroundFeature(@PathVariable UUID id) {
        backgroundFeatureService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public BackgroundFeature updateBackgroundFeature(@PathVariable UUID id, @Validated @RequestBody BackgroundFeatureDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return backgroundFeatureService.findByIdAndUpdate(id, body);
    }
}
