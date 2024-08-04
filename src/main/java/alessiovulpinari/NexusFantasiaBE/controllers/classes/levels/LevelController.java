package alessiovulpinari.NexusFantasiaBE.controllers.classes.levels;

import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.Level;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.ClassFeatureNameDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.classes.levels.*;
import alessiovulpinari.NexusFantasiaBE.services.classes.levels.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/levels")
@CrossOrigin(origins = "http://localhost:5173")
public class LevelController {

    @Autowired
    private LevelService levelService;

    @Autowired
    private CasterLevelService casterLevelService;

    @Autowired
    private MonkLevelService monkLevelService;

    @Autowired
    private RogueLevelService rogueLevelService;

    @Autowired
    private SorcererLevelService sorcererLevelService;

    @Autowired
    private WarlockLevelService warlockLevelService;

    //TODO da sostituire con la risposta DTO
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    Level createLevel(@RequestBody @Validated LevelDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return levelService.saveLevel(body);
    }

    //TODO da sostituire con la risposta DTO
    @PostMapping("/caster")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    Level createCasterLevel(@RequestBody @Validated CasterLevelDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return casterLevelService.saveLevel(body);
    }

    //TODO da sostituire con la risposta DTO
    @PostMapping("/monk")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    Level createMonkLevel(@RequestBody @Validated MonkLevelDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return monkLevelService.saveLevel(body);
    }

    //TODO da sostituire con la risposta DTO
    @PostMapping("/rogue")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    Level createRogueLevel(@RequestBody @Validated RogueLevelDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return rogueLevelService.saveLevel(body);
    }

    //TODO da sostituire con la risposta DTO
    @PostMapping("/sorcerer")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    Level createSorcererLevel(@RequestBody @Validated SorcererLevelDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return sorcererLevelService.saveLevel(body);
    }

    //TODO da sostituire con la risposta DTO
    @PostMapping("/warlock")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    Level createWarlockLevel(@RequestBody @Validated WarlockLevelDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return warlockLevelService.saveLevel(body);
    }


    @GetMapping
    Page<Level> getAllLevels(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return levelService.getLevels(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Level getLevelById(@PathVariable UUID id) {
        return levelService.getLevelById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevel(@PathVariable UUID id) {
        levelService.findByIdAndDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Level updateLevel(@PathVariable UUID id, @Validated @RequestBody LevelDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return levelService.findByIdAndUpdate(id, body);
    }

    @PutMapping("/{id}/features/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Level addFeature(@PathVariable UUID id, @Validated @RequestBody ClassFeatureNameDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return levelService.addClassFeature(id, body);
    }

    @PutMapping("caster/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Level updateCasterLevel(@PathVariable UUID id, @Validated @RequestBody CasterLevelDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return casterLevelService.findByIdAndUpdate(id, body);
    }


    @PutMapping("monk/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Level updateMonkLevel(@PathVariable UUID id, @Validated @RequestBody MonkLevelDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return monkLevelService.findByIdAndUpdate(id, body);
    }


    @PutMapping("rogue/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Level updateRogueLevel(@PathVariable UUID id, @Validated @RequestBody RogueLevelDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return rogueLevelService.findByIdAndUpdate(id, body);
    }


    @PutMapping("sorcerer/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Level updateSorcererLevel(@PathVariable UUID id, @Validated @RequestBody SorcererLevelDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return sorcererLevelService.findByIdAndUpdate(id, body);
    }


    @PutMapping("warlock/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    Level updateWarlockLevel(@PathVariable UUID id, @Validated @RequestBody WarlockLevelDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return warlockLevelService.findByIdAndUpdate(id, body);
    }



}
