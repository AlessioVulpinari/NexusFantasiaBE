package alessiovulpinari.NexusFantasiaBE.payloads.races;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record IncrementedScoreRaceDTO(@NotEmpty(message = "Il nome dell'abilità non deve essere vuoto!") String abilityScoreName,
                                      @NotNull(message = "Il valore dell'incremento non deve essere nullo!") Integer incrementValue,
                                      @NotEmpty(message = "Il nome della razza non deve essere vuoto!") String raceName) { }
