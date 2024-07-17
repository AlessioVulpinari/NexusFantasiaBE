package alessiovulpinari.NexusFantasiaBE.entities.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Table(name = "livelli_incantatore")
@Getter
@Setter
@NoArgsConstructor
public class CasterLevel extends Level {

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

    @Column(name = "slot_magie_quinto_livello")
    private int fifthSlotSpell;

    @Column(name = "slot_magie_sesto_livello")
    private int sixthSlotSpell;

    @Column(name = "slot_magie_settimo_livello")
    private int seventhSlotSpell;

    @Column(name = "slot_magie_ottavo_livello")
    private int eighthSlotSpell;

    @Column(name = "slot_magie_nono_livello")
    private int ninthSlotSpell;

    public CasterLevel(int levelNumber, int proficiencyBonus, Class aClass, int cantripsKnown, int spellsKnown, int fistSlotSpell, int secondSlotSpell, int thirdSlotSpell, int fourthSlotSpell, int fifthSlotSpell, int sixthSlotSpell, int seventhSlotSpell, int eighthSlotSpell, int ninthSlotSpell) {
        super(levelNumber, proficiencyBonus, aClass);
        this.cantripsKnown = cantripsKnown;
        this.spellsKnown = spellsKnown;
        this.fistSlotSpell = fistSlotSpell;
        this.secondSlotSpell = secondSlotSpell;
        this.thirdSlotSpell = thirdSlotSpell;
        this.fourthSlotSpell = fourthSlotSpell;
        this.fifthSlotSpell = fifthSlotSpell;
        this.sixthSlotSpell = sixthSlotSpell;
        this.seventhSlotSpell = seventhSlotSpell;
        this.eighthSlotSpell = eighthSlotSpell;
        this.ninthSlotSpell = ninthSlotSpell;
    }

}
