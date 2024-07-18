package alessiovulpinari.NexusFantasiaBE.payloads.classes;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ClassDTO(@NotEmpty(message = "II nome della classe non può essere vuoto!") String className,
                       @NotNull(message = "Il livello della sotto classe non può essere nullo!") Integer levelForSubClass,
                       @NotNull(message = "Il dado vota della classe non può essere nullo!") Integer hitDice) {
}
