package alessiovulpinari.NexusFantasiaBE.payloads.races;

import jakarta.validation.constraints.NotEmpty;

public record RacialTraitDTO(@NotEmpty(message = "Il nome del tratto non può essere vuoto!") String name,
                             @NotEmpty(message = "La descrizione del tratto non può essere vuota!") String description) {
}
