package alessiovulpinari.NexusFantasiaBE.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "livelli_warlock")
public class WarlockLevel extends Level{

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

    public WarlockLevel(int levelNumber, int proficiencyBonus, int cantripsKnown, int spellsKnown, int spellSlots, int slotLevel, int invocationsKnown) {
        super(levelNumber, proficiencyBonus);
        this.cantripsKnown = cantripsKnown;
        this.spellsKnown = spellsKnown;
        this.spellSlots = spellSlots;
        this.slotLevel = slotLevel;
        this.invocationsKnown = invocationsKnown;
    }
}
