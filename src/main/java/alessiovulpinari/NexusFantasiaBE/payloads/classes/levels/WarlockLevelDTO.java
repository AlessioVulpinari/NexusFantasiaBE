package alessiovulpinari.NexusFantasiaBE.payloads.classes.levels;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record WarlockLevelDTO(@NotNull(message = "Il numero del livello non deve essere nullo!") Integer levelNumber,
                              @NotNull(message = "Il numero della competenza non deve essere nullo!") Integer proficiencyBonus,
                              @NotEmpty(message = "Il nome della classe non deve essere vuoto!") String className,
                              @NotNull(message = "Il numero dei trucchetti conosciuti non può essere nullo!") Integer cantripsKnown,
                              @NotNull(message = "Il numero di incantesimi conosciuti non può essere nullo!") Integer spellsKnown,
                              @NotNull(message = "Il numero di slot incantesimo non può essere nullo!") Integer spellSlots,
                              @NotNull(message = "Il numero del livello degli slot incantesimo non può essere nullo!") Integer slotLevel,
                              @NotNull(message = "Il numero di invocazioni conosciute non può essere nullo!") Integer invocationsKnown) {
}
