package alessiovulpinari.NexusFantasiaBE.entities.classes.levels;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Class;
import alessiovulpinari.NexusFantasiaBE.entities.classes.ClassFeature;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_livello", nullable = false)
    private UUID levelId;

    @Column(name = "numero_livello")
    private int levelNumber;

    @Column(name = "bonus_competenza", nullable = false)
    private int proficiencyBonus;

    @ManyToOne
    @JoinColumn(name="id_classe")
    private Class aClass;

    @ManyToMany
    @JoinTable(
            name = "livello_tratti",
            joinColumns = @JoinColumn(name = "id_livello"),
            inverseJoinColumns = @JoinColumn(name = "id_tratto"))
    Set<ClassFeature> classFeatures;

    public Level(int levelNumber, int proficiencyBonus, Class aClass) {
        this.levelNumber = levelNumber;
        this.aClass = aClass;
        this.proficiencyBonus = proficiencyBonus;
        this.classFeatures = new HashSet<>();
    }
}
