package alessiovulpinari.NexusFantasiaBE.payloads.sheets;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BackgroundDTO(@NotEmpty(message = "Il nome del background non può essere vuoto!") String name,
                            @NotEmpty(message = "La descrizione del background non può essere vuota!") String description,
                            @NotNull(message = "Il numero delle lingue extra non può essere nullo!") Integer extraLanguages) {
}
