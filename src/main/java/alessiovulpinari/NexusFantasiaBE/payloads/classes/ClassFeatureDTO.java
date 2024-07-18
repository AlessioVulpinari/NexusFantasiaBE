package alessiovulpinari.NexusFantasiaBE.payloads.classes;

import jakarta.validation.constraints.NotEmpty;

public record ClassFeatureDTO(@NotEmpty(message = "Il nome del tratto di classi non può essere vuoto!") String classFeatureName,
                              @NotEmpty(message = "La descrizione del tratto di classe non può essere vuoto!") String classFeatureDescription) {
}
