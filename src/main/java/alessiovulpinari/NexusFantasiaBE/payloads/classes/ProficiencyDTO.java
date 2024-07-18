package alessiovulpinari.NexusFantasiaBE.payloads.classes;

import jakarta.validation.constraints.NotEmpty;

public record ProficiencyDTO(@NotEmpty(message = "Il tipo della competenza non può essere nullo!") String proficiencyType,
                             @NotEmpty(message = "Il nome della competenza non può essere nullo!") String name,
                             @NotEmpty(message = "La descrizione della competenza non può essere nullo!") String description) {
}
