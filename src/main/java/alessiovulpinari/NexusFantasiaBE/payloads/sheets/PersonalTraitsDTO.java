package alessiovulpinari.NexusFantasiaBE.payloads.sheets;

import jakarta.validation.constraints.NotEmpty;

public record PersonalTraitsDTO(@NotEmpty(message = "Il carattere del personaggio non può essere vuoto!") String personalTraits,
                                @NotEmpty(message = "Gli ideali del personaggio non possono essere vuoti") String ideals,
                                @NotEmpty(message = "I legami del personaggio non possono essere vuoti") String bonds,
                                @NotEmpty(message = "I difetti del personaggio non possono essere vuoti") String flaw,
                                @NotEmpty(message = "Il background non può essere vuoto!") String backgroundName)
{
}
