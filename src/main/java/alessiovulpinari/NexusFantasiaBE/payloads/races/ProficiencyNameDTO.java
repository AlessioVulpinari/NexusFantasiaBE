package alessiovulpinari.NexusFantasiaBE.payloads.races;

import jakarta.validation.constraints.NotEmpty;

public record ProficiencyNameDTO(@NotEmpty(message = "Il nome della competenza non può essere vuoto!") String proficiencyName) {
}
