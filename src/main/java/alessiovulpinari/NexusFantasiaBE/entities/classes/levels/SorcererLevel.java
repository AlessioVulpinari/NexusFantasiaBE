package alessiovulpinari.NexusFantasiaBE.entities.classes.levels;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Class;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SorcererLevel extends CasterLevel {

    @Column(name = "punti_stregoneria")
    private int sorceryPoints;

    public SorcererLevel(int levelNumber, int proficiencyBonus, Class aClass, int cantripsKnown, int spellsKnown, int fistSlotSpell, int secondSlotSpell, int thirdSlotSpell, int fourthSlotSpell, int fifthSlotSpell, int sixthSlotSpell, int seventhSlotSpell, int eighthSlotSpell, int ninthSlotSpell, int sorceryPoints) {
        super(levelNumber, proficiencyBonus, aClass, cantripsKnown, spellsKnown, fistSlotSpell, secondSlotSpell, thirdSlotSpell, fourthSlotSpell, fifthSlotSpell, sixthSlotSpell, seventhSlotSpell, eighthSlotSpell, ninthSlotSpell);
        this.sorceryPoints = sorceryPoints;
    }
}
