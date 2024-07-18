package alessiovulpinari.NexusFantasiaBE.entities.classes.levels;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Class;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class WarlockLevel extends Level {

    @Column(name = "trucchetti_conosciuti")
    private int cantripsKnown;

    @Column(name = "magie_conosciute")
    private int spellsKnown;

    @Column(name = "slot_magie")
    private int spellSlots;

    @Column(name = "livello_slot")
    private int slotLevel;

    @Column(name = "invocazioni_conosciute")
    private int invocationsKnown;

    public WarlockLevel(int levelNumber, int proficiencyBonus, Class aClass, int cantripsKnown, int spellsKnown, int spellSlots, int slotLevel, int invocationsKnown) {
        super(levelNumber, proficiencyBonus, aClass);
        this.cantripsKnown = cantripsKnown;
        this.spellsKnown = spellsKnown;
        this.spellSlots = spellSlots;
        this.slotLevel = slotLevel;
        this.invocationsKnown = invocationsKnown;
    }
}
