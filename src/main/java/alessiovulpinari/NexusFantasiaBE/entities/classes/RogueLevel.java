package alessiovulpinari.NexusFantasiaBE.entities.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Table(name = "livelli_ladro")
@Getter
@Setter
@NoArgsConstructor
public class RogueLevel extends Level {

    @Column(name = "numeri_dadi_furtivo" , nullable = false)
    private int sneakAttackNumberDice;
    @Column(name = "numeri_dadi_danno_furtivo" , nullable = false)
    private int sneakAttackDamageDice;
}
