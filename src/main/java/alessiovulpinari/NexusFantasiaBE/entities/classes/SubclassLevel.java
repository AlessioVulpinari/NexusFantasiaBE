package alessiovulpinari.NexusFantasiaBE.entities.classes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "livelli_sottoclassi")
public class SubclassLevel {

    @Id
    @GeneratedValue
    @Column(name = "id_livello_sottoclasse", nullable = false)
    private UUID subclassLevelId;

    @Column(name = "numero_livello")
    private int levelNumber;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "tratto_sottoclasse")
    private ClassFeature classFeature;

    public SubclassLevel(int levelNumber, ClassFeature classFeature) {
        this.levelNumber = levelNumber;
        this.classFeature = classFeature;
    }
}
