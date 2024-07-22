package alessiovulpinari.NexusFantasiaBE.payloads.races;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record IncrementedScoreFeatDTO(@NotEmpty(message = "Il nome dell'abilità non deve essere vuoto!") String abilityScoreName,
                                      @NotNull(message = "Il valore dell'incremento non deve essere nullo!") Integer incrementValue,
                                      @NotEmpty(message = "Il nome del talento non deve essere vuoto!") String featName) {
}
