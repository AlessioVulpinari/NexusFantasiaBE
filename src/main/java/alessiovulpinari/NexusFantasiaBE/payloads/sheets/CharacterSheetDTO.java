package alessiovulpinari.NexusFantasiaBE.payloads.sheets;

import jakarta.validation.constraints.NotEmpty;

public record CharacterSheetDTO(@NotEmpty(message = "Il nome del personaggio della scheda non può essere nullo!") String name) {
}
