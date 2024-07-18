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
public class MonkLevel extends Level {

    @Column(name = "arte_marziale")
    private int martialArt;

    @Column(name = "punti_ki")
    private int kiPoints;

    @Column(name = "movimento_senza_armatura")
    private double unarmedMovement;

    public MonkLevel(int levelNumber, int proficiencyBonus, Class aClass, int martialArt, int kiPoints, double unarmedMovement) {
        super(levelNumber, proficiencyBonus, aClass);
        this.martialArt = martialArt;
        this.kiPoints = kiPoints;
        this.unarmedMovement = unarmedMovement;
    }
}
