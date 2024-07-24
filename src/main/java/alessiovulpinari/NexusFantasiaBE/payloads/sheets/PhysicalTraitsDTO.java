package alessiovulpinari.NexusFantasiaBE.payloads.sheets;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PhysicalTraitsDTO(@NotNull(message = "L'età del personaggio non può essere nulla!") Integer age,
                                @NotEmpty(message = "L'altezza del personaggio non può essere vuota!") String height,
                                @NotEmpty(message = "Il peso del personaggio non può essere vuoto!") String weight,
                                @NotEmpty(message = "Il colore degli occhi del personaggio non può essere vuoto!") String eyeColor,
                                @NotEmpty(message = "Il colore dei capelli del personaggio non può essere vuoto!") String hairColor,
                                @NotEmpty(message = "La carnagione del personaggio non può essere vuota!") String complexion) {
}
