package alessiovulpinari.NexusFantasiaBE.controllers.sheet;

import alessiovulpinari.NexusFantasiaBE.entities.User;
import alessiovulpinari.NexusFantasiaBE.entities.sheet.CharacterSheet;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.sheets.AddClassDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.sheets.CharacterSheetDTO;
import alessiovulpinari.NexusFantasiaBE.services.sheets.CharacterSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/characterSheets")
@CrossOrigin(origins = "http://localhost:5173")
public class CharacterSheetController {

    @Autowired
    private CharacterSheetService characterSheetService;

    @GetMapping
    public Page<CharacterSheet> getAllCharacterSheetOfUser(@AuthenticationPrincipal User user, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return characterSheetService.getAllUserCharacterSheets(page, size, user);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CharacterSheet getCharacterSheetById(@PathVariable UUID id) {
        return characterSheetService.getCharacterSheetById(id);
    }

    //TODO da sostituire con la risposta DTO
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public CharacterSheet createCharacterSheet(@AuthenticationPrincipal User user, @RequestBody @Validated CharacterSheetDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return characterSheetService.saveCharacterSheet(body, user);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public CharacterSheet updateCharacterSheet(@AuthenticationPrincipal User user, @PathVariable UUID id, @Validated @RequestBody CharacterSheetDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return characterSheetService.updateCharacterSheet(id, body, user);
    }

    @PutMapping("/{id}/levels")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public CharacterSheet addClassLevel(@AuthenticationPrincipal User user, @PathVariable UUID id, @Validated @RequestBody AddClassDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return characterSheetService.addClassLevel(id, body, user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCharacterSheet(@AuthenticationPrincipal User user, @PathVariable UUID id) {
        characterSheetService.findByIdAndDelete(id, user);
    }

}
