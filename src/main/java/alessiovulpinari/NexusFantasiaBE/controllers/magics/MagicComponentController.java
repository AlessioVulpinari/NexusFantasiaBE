package alessiovulpinari.NexusFantasiaBE.controllers.magics;

import alessiovulpinari.NexusFantasiaBE.entities.magics.MagicComponent;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.magics.MagicComponentDTO;
import alessiovulpinari.NexusFantasiaBE.services.magics.MagicComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/magicComponents")
@CrossOrigin(origins = "http://localhost:5173")
public class MagicComponentController {

    @Autowired
    private MagicComponentService magicComponentService;

    //TODO da sostituire con la risposta DTO
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    MagicComponent createMagicComponent(@RequestBody @Validated MagicComponentDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return magicComponentService.saveMagicComponent(body);
    }

    @GetMapping
    Page<MagicComponent> getAllMagicComponents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return magicComponentService.getMagicComponents(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MagicComponent getMagicComponentById(@PathVariable UUID id) {
        return magicComponentService.getComponentsById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMagicComponent(@PathVariable UUID id) {
        magicComponentService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    MagicComponent updateMagicComponent(@PathVariable UUID id, @Validated @RequestBody MagicComponentDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return magicComponentService.findByIdAndUpdate(id, body);
    }

}
