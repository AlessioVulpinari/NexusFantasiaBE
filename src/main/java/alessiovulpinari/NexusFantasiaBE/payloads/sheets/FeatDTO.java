package alessiovulpinari.NexusFantasiaBE.payloads.sheets;

import jakarta.validation.constraints.NotEmpty;

public record FeatDTO(@NotEmpty(message = "Il nome del talento non può essere vuoto!") String name,
                      @NotEmpty(message = "La descrizione dei prerequisiti non può essere vuoto!") String prerequisite,
                      @NotEmpty(message = "La descrizione del talento non può essere vuota!") String description) {
}
