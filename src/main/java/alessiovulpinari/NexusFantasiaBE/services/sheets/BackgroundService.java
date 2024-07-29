package alessiovulpinari.NexusFantasiaBE.services.sheets;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Proficiency;
import alessiovulpinari.NexusFantasiaBE.entities.equipments.Equipment;
import alessiovulpinari.NexusFantasiaBE.entities.sheet.Background;
import alessiovulpinari.NexusFantasiaBE.entities.sheet.BackgroundFeature;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.equipments.EquipmentNameDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.races.ProficiencyNameDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.sheets.BackgroundDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.sheets.BackgroundFeatureNameDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.sheet.BackgroundRepository;
import alessiovulpinari.NexusFantasiaBE.services.classes.ProficiencyService;
import alessiovulpinari.NexusFantasiaBE.services.equipments.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BackgroundService {

    @Autowired
    private BackgroundRepository backgroundRepository;

    @Autowired
    private ProficiencyService proficiencyService;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private BackgroundFeatureService backgroundFeatureService;

    public Page<Background> getBackgrounds(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return backgroundRepository.findAll(pageable);
    }

    public Background getBackgroundById(UUID uuid) {
        return backgroundRepository.findById(uuid).orElseThrow(() -> new NotFoundException("Nessun background con questo id trovato!"));
    }

    public Background saveBackground(BackgroundDTO body) {
        this.backgroundRepository.findByName(body.name()).ifPresent
                (background -> {throw new BadRequestException("Esiste già un background con questo nome: " + body.name());});

        Background background = new Background(body.name(), body.description(), body.extraLanguages());
        return  this.backgroundRepository.save(background);
    }

    public Background findByIdAndUpdate(UUID backgroundId, BackgroundDTO body) {

        Background found = this.getBackgroundById(backgroundId);
        found.setName(body.name());
        found.setDescription(body.description());
        found.setExtraLanguages(body.extraLanguages());

        return this.backgroundRepository.save(found);
    }

    public void findByIdAndDelete(UUID backgroundId) {
        Background found = getBackgroundById(backgroundId);
        this.backgroundRepository.delete(found);
    }

    public Background getBackgroundByName(String name) {
      return this.backgroundRepository.findByName(name).orElseThrow(()-> new NotFoundException("Background con questo nome non trovato!"));
    }

    public Background addProficiency(UUID backgroundId, ProficiencyNameDTO body) {
        Background found = this.getBackgroundById(backgroundId);
        Proficiency proficiency = this.proficiencyService.findByProficiencyName(body.proficiencyName());

        found.addProficiency(proficiency);
        return this.backgroundRepository.save(found);
    }

    public Background removeProficiency(UUID backgroundId, ProficiencyNameDTO body) {
        Background found = this.getBackgroundById(backgroundId);
        Proficiency proficiency = this.proficiencyService.findByProficiencyName(body.proficiencyName());

        found.removeProficiency(proficiency);
        return this.backgroundRepository.save(found);
    }

    public Background addEquipment(UUID backgroundId, EquipmentNameDTO body) {
        Background found = this.getBackgroundById(backgroundId);
        Equipment equipment = this.equipmentService.findByName(body.name());

        found.addEquipment(equipment);
        return this.backgroundRepository.save(found);
    }

    public Background removeEquipment(UUID backgroundId, EquipmentNameDTO body) {
        Background found = this.getBackgroundById(backgroundId);
        Equipment equipment = this.equipmentService.findByName(body.name());

        found.removeEquipment(equipment);
        return this.backgroundRepository.save(found);
    }

    public Background addBackgroundFeatures(UUID backgroundId, BackgroundFeatureNameDTO body) {
        Background found = this.getBackgroundById(backgroundId);
        BackgroundFeature backgroundFeature = this.backgroundFeatureService.findByName(body.backgroundFeatureName());

        found.addBackgroundFeature(backgroundFeature);
        return this.backgroundRepository.save(found);
    }

    public Background removeBackgroundFeatures(UUID backgroundId, BackgroundFeatureNameDTO body) {
        Background found = this.getBackgroundById(backgroundId);
        BackgroundFeature backgroundFeature = this.backgroundFeatureService.findByName(body.backgroundFeatureName());

        found.removeBackgroundFeature(backgroundFeature);
        return this.backgroundRepository.save(found);
    }

}
