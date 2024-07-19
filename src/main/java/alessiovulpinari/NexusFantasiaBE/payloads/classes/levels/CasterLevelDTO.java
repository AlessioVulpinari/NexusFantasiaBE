package alessiovulpinari.NexusFantasiaBE.payloads.classes.levels;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CasterLevelDTO(@NotNull(message = "Il numero del livello non deve essere nullo!") Integer levelNumber,
                             @NotNull(message = "Il numero della competenza non deve essere nullo!") Integer proficiencyBonus,
                             @NotEmpty(message = "Il nome della classe non deve essere vuoto!") String className,
                             @NotNull(message = "Il numero di trucchetti conosciuti non può essere nullo!") Integer cantripsKnown,
                             @NotNull(message = "Il numero di incantesimi conosciuti non può essere nullo!") Integer spellsKnown,
                             @NotNull(message = "Il numero di slot degli incantesimi di primo livello non può essere nullo!") Integer fistSlotSpell,
                             Integer secondSlotSpell,
                             Integer thirdSlotSpell,
                             Integer fourthSlotSpell,
                             Integer fifthSlotSpell,
                             Integer sixthSlotSpell,
                             Integer seventhSlotSpell,
                             Integer eighthSlotSpell,
                             Integer ninthSlotSpell) {}
