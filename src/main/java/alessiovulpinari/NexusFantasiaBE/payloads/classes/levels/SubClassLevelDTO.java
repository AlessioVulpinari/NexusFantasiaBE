package alessiovulpinari.NexusFantasiaBE.payloads.classes.levels;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SubClassLevelDTO(@NotNull(message = "Il numero del livello non deve essere nullo!")  Integer levelNumber,
                               @NotEmpty(message = "Il nome del tratto della sottoclasse non deve essere nullo!") String classFeature,
                               @NotEmpty(message = "Il nome della classe non deve essere vuoto!") String subClassName) {
}
