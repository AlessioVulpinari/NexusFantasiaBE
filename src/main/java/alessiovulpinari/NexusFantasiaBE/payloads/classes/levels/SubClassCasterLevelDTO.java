package alessiovulpinari.NexusFantasiaBE.payloads.classes.levels;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SubClassCasterLevelDTO(@NotNull(message = "Il numero del livello non deve essere nullo!")  Integer levelNumber,
                                     @NotEmpty(message = "Il nome del tratto della sottoclasse non deve essere nullo!") String classFeature,
                                     @NotEmpty(message = "Il nome della classe non deve essere vuoto!") String subClassName ,
                                     @NotNull(message = "Il numero di trucchetti conosciuti non può essere nullo!") Integer cantripsKnown,
                                     @NotNull(message = "Il numero di incantesimi conosciuti non può essere nullo!")  Integer spellsKnown,
                                     @NotNull(message = "Il numero di slot degli incantesimi di primo livello non può essere nullo!") Integer fistSlotSpell,
                                     Integer secondSlotSpell,
                                     Integer thirdSlotSpell,
                                     Integer fourthSlotSpell) {
}
