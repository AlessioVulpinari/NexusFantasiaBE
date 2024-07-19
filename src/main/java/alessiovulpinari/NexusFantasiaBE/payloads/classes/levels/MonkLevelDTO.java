package alessiovulpinari.NexusFantasiaBE.payloads.classes.levels;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record MonkLevelDTO(@NotNull(message = "Il numero del livello non deve essere nullo!") Integer levelNumber,
                           @NotNull(message = "Il numero della competenza non deve essere nullo!") Integer proficiencyBonus,
                           @NotEmpty(message = "Il nome della classe non deve essere vuoto!") String className,
                           @NotNull(message = "Il valore del dado dei danni da disarmato non può essere nullo!") Integer martialArt,
                           @NotNull(message = "I punti Ki non possono essere nulli!") Integer kiPoints,
                           @NotNull(message = "Il movimento bonus non può essere vuoto!") Double unarmedMovement) {
}
