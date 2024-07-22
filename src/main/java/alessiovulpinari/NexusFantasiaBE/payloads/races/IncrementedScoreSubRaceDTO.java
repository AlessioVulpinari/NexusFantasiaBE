package alessiovulpinari.NexusFantasiaBE.payloads.races;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record IncrementedScoreSubRaceDTO(@NotEmpty(message = "Il nome dell'abilità non deve essere vuoto!") String abilityScoreName,
                                         @NotNull(message = "Il valore dell'incremento non deve essere nullo!") Integer incrementValue,
                                         @NotEmpty(message = "Il nome della sotto razza non deve essere vuoto!") String subRaceName) {
}
