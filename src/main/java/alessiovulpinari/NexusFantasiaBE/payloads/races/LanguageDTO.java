package alessiovulpinari.NexusFantasiaBE.payloads.races;

import jakarta.validation.constraints.NotEmpty;

public record LanguageDTO(@NotEmpty(message = "Il nome del linguaggio non deve essere vuoto!") String name,
                          @NotEmpty(message = "La descrizione del linguaggio non deve essere vuoto!") String description) {
}
