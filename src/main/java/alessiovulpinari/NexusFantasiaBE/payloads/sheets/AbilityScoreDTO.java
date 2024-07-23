package alessiovulpinari.NexusFantasiaBE.payloads.sheets;

import jakarta.validation.constraints.NotEmpty;

public record AbilityScoreDTO(@NotEmpty(message = "Il nome della caratteristica non può essere vuoto!") String name,
                              @NotEmpty(message = "La descrizione della caratteristica non può essere vuota!") String description) {
}
