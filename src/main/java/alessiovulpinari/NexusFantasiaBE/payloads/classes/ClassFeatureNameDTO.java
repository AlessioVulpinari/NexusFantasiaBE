package alessiovulpinari.NexusFantasiaBE.payloads.classes;

import jakarta.validation.constraints.NotEmpty;

public record ClassFeatureNameDTO(@NotEmpty(message = "Il nome non deve essere vuoto!") String name) {
}
