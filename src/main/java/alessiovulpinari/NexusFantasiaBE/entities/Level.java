package alessiovulpinari.NexusFantasiaBE.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "livelli")
@Getter
@Setter
@NoArgsConstructor
public class Level {

    @Id
    @GeneratedValue
    @Column(name = "id_livello", nullable = false)
    private UUID levelId;

    @Column(name = "numero_livello")
    private int levelNumber;

    @Column(name = "bonus_competenza", nullable = false)
    private int proficiencyBonus;

    public Level(int levelNumber, int proficiencyBonus) {
        this.levelNumber = levelNumber;
        this.proficiencyBonus = proficiencyBonus;
        this.classFeatures = new HashSet<>();
    }

    @ManyToMany
    @JoinTable(
            name = "livello_tratti",
            joinColumns = @JoinColumn(name = "id_livello"),
            inverseJoinColumns = @JoinColumn(name = "id_tratto"))
    Set<ClassFeature> classFeatures;
}
