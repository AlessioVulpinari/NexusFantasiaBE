package alessiovulpinari.NexusFantasiaBE.payloads.classes.levels;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LevelDTO(@NotNull(message = "Il numero del livello non deve essere nullo!") Integer levelNumber,
                       @NotNull(message = "Il numero della competenza non deve essere nullo!") Integer proficiencyBonus,
                       @NotEmpty(message = "Il nome della classe non deve essere vuoto!") String className) {
}
