package alessiovulpinari.NexusFantasiaBE.controllers.races;

import alessiovulpinari.NexusFantasiaBE.entities.races.Languages;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.races.LanguageDTO;
import alessiovulpinari.NexusFantasiaBE.services.races.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/languages")
@CrossOrigin(origins = "http://localhost:5173")
public class LanguagesController {

    @Autowired
    private LanguageService languageService;

    //TODO da sostituire con la risposta DTO
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    Languages createLanguage(@RequestBody @Validated LanguageDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return languageService.saveLanguage(body);
    }

    @GetMapping
    Page<Languages> getAllLanguages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return languageService.getLanguages(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Languages getLanguageById(@PathVariable UUID id) {
        return languageService.getLanguageById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLanguage(@PathVariable UUID id) {
        languageService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Languages updateLanguage(@PathVariable UUID id, @Validated @RequestBody LanguageDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return languageService.findByIdAndUpdate(id, body);
    }
}
