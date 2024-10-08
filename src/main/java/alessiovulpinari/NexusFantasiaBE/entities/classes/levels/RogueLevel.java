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
public class RogueLevel extends Level {

    @Column(name = "numeri_dadi_furtivo" )
    private int sneakAttackNumberDice;

    @Column(name = "numeri_dadi_danno_furtivo")
    private int sneakAttackDamageDice;

    public RogueLevel(int levelNumber, int proficiencyBonus, Class aClass, int sneakAttackNumberDice, int sneakAttackDamageDice) {
        super(levelNumber, proficiencyBonus, aClass);
        this.sneakAttackNumberDice = sneakAttackNumberDice;
        this.sneakAttackDamageDice = sneakAttackDamageDice;
    }
}
