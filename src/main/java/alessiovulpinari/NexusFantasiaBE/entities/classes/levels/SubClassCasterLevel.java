package alessiovulpinari.NexusFantasiaBE.entities.classes.levels;

import alessiovulpinari.NexusFantasiaBE.entities.classes.ClassFeature;
import alessiovulpinari.NexusFantasiaBE.entities.classes.Subclass;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SubClassCasterLevel extends SubclassLevel {

    @Column(name = "trucchetti_conosciuti")
    private int cantripsKnown;

    @Column(name = "magie_conosciute")
    private int spellsKnown;

    @Column(name = "slot_magie_primo_livello")
    private int fistSlotSpell;

    @Column(name = "slot_magie_secondo_livello")
    private int secondSlotSpell;

    @Column(name = "slot_magie_terzo_livello")
    private int thirdSlotSpell;

    @Column(name = "slot_magie_quarto_livello")
    private int fourthSlotSpell;

    public SubClassCasterLevel(int levelNumber, ClassFeature classFeature, Subclass subclass, int cantripsKnown, int spellsKnown, int fistSlotSpell, int secondSlotSpell, int thirdSlotSpell, int fourthSlotSpell) {
        super(levelNumber, classFeature, subclass);
        this.cantripsKnown = cantripsKnown;
        this.spellsKnown = spellsKnown;
        this.fistSlotSpell = fistSlotSpell;
        this.secondSlotSpell = secondSlotSpell;
        this.thirdSlotSpell = thirdSlotSpell;
        this.fourthSlotSpell = fourthSlotSpell;
    }
}
