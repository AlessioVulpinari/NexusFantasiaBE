package alessiovulpinari.NexusFantasiaBE.payloads.sheets;

import jakarta.validation.constraints.NotEmpty;

public record BackgroundFeatureDTO(@NotEmpty(message = "Il nome del tratto di background non può essere vuoto!") String name,
                                   @NotEmpty(message = "La descrizione del tratto di background non può essere vuota!") String description) {
}
