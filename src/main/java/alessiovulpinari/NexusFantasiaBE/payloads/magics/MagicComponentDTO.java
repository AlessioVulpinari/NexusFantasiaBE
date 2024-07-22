package alessiovulpinari.NexusFantasiaBE.payloads.magics;

import jakarta.validation.constraints.NotEmpty;

public record MagicComponentDTO(@NotEmpty(message = "Il nome del componente magico non può essere vuoto!") String name,
                                @NotEmpty(message = "La descrizione del componente magico non può essere vuoto!") String description,
                                @NotEmpty(message = "Il simbolo del componente magico non può essere vuoto!") String componentSymbol) {
}
