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
@Table(name = "livelli_monaco")
public class MonkLevel extends Level {

    @Column(name = "arte_marziale")
    private int martialArt;

    @Column(name = "punti_ki")
    private int kiPoints;

    @Column(name = "movimento_senza_armatura")
    private double unarmedMovement;

    public MonkLevel(int levelNumber, int proficiencyBonus, int martialArt, int kiPoints, double unarmedMovement) {
        super(levelNumber, proficiencyBonus);
        this.martialArt = martialArt;
        this.kiPoints = kiPoints;
        this.unarmedMovement = unarmedMovement;
    }
}
