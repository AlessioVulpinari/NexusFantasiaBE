package alessiovulpinari.NexusFantasiaBE.entities.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Table(name = "livelli_stregone")
@Getter
@Setter
@NoArgsConstructor
public class SorcererLevel extends CasterLevel {

    @Column(name = "punti_stregoneria")
    private int sorceryPoints;

    public SorcererLevel(int levelNumber, int proficiencyBonus, int cantripsKnown, int spellsKnown, int fistSlotSpell, int secondSlotSpell, int thirdSlotSpell, int fourthSlotSpell, int fifthSlotSpell, int sixthSlotSpell, int seventhSlotSpell, int eighthSlotSpell, int ninthSlotSpell, int sorceryPoints) {
        super(levelNumber, proficiencyBonus, cantripsKnown, spellsKnown, fistSlotSpell, secondSlotSpell, thirdSlotSpell, fourthSlotSpell, fifthSlotSpell, sixthSlotSpell, seventhSlotSpell, eighthSlotSpell, ninthSlotSpell);
        this.sorceryPoints = sorceryPoints;
    }
}
