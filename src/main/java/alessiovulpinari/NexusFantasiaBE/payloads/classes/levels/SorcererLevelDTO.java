package alessiovulpinari.NexusFantasiaBE.payloads.classes.levels;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SorcererLevelDTO(@NotNull(message = "Il numero del livello non deve essere nullo!") Integer levelNumber,
                               @NotNull(message = "Il numero della competenza non deve essere nullo!") Integer proficiencyBonus,
                               @NotEmpty(message = "Il nome della classe non deve essere vuoto!") String className,
                               @NotNull Integer cantripsKnown,
                               @NotNull Integer spellsKnown,
                               @NotNull Integer fistSlotSpell,
                               Integer secondSlotSpell,
                               Integer thirdSlotSpell,
                               Integer fourthSlotSpell,
                               Integer fifthSlotSpell,
                               Integer sixthSlotSpell,
                               Integer seventhSlotSpell,
                               Integer eighthSlotSpell,
                               Integer ninthSlotSpell,
                               @NotNull(message = "I punti stregoneria non possono essere nulli!") Integer sorceryPoints) {
}
