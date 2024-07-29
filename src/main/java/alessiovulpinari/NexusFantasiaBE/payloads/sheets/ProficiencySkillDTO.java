package alessiovulpinari.NexusFantasiaBE.payloads.sheets;

import jakarta.validation.constraints.NotEmpty;

public record ProficiencySkillDTO(@NotEmpty(message = "Il nome della competenza non può essere nullo!") String name,
                                  @NotEmpty(message = "La descrizione della competenza non può essere nullo!") String description,
                                  @NotEmpty(message = "Il nome della caratteristica non può essere nullo!") String abilityScoreName) {
}
