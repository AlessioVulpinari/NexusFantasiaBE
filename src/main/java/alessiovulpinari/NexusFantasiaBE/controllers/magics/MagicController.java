package alessiovulpinari.NexusFantasiaBE.controllers.magics;

import alessiovulpinari.NexusFantasiaBE.entities.magics.Magic;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.ClassNameDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.magics.MagicComponentNameDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.magics.MagicDTO;
import alessiovulpinari.NexusFantasiaBE.services.magics.MagicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/magics")
@CrossOrigin(origins = "http://localhost:5173")
public class MagicController {

    @Autowired
    private MagicService magicService;

    //TODO da sostituire con la risposta DTO
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    Magic createMagic(@RequestBody @Validated MagicDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return magicService.saveMagic(body);
    }

    @GetMapping
    Page<Magic> getAllMagics(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return magicService.getMagics(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Magic getMagicById(@PathVariable UUID id) {
        return magicService.getMagicById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMagic(@PathVariable UUID id) {
        magicService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Magic updateMagic(@PathVariable UUID id, @Validated @RequestBody MagicDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return magicService.findByIdAndUpdate(id, body);
    }

    @PutMapping("/{id}/components/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Magic addMagicComponentToAMagic(@PathVariable UUID id, @Validated @RequestBody MagicComponentNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return magicService.addMagicComponent(id, body);
    }

    @PutMapping("/{id}/components/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Magic removeMagicComponentToAMagic(@PathVariable UUID id, @Validated @RequestBody MagicComponentNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return magicService.removeMagicComponent(id, body);
    }

    @PutMapping("/{id}/classes/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Magic addClassToSpellList(@PathVariable UUID id, @Validated @RequestBody ClassNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return magicService.addClassToSpellList(id, body);
    }

    @PutMapping("/{id}/classes/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Magic removeClassToSpellList(@PathVariable UUID id, @Validated @RequestBody ClassNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return magicService.removeClassToSpellList(id, body);
    }

    @PutMapping("/{id}/subclasses/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Magic addSubClassToSpellList(@PathVariable UUID id, @Validated @RequestBody ClassNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return magicService.addSubClassToSpellList(id, body);
    }

    @PutMapping("/{id}/subclasses/remove")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Magic removeSubClassToSpellList(@PathVariable UUID id, @Validated @RequestBody ClassNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return magicService.removeSubClassToSpellList(id, body);
    }


}
