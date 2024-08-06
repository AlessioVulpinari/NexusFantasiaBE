package alessiovulpinari.NexusFantasiaBE.entities.classes.levels;

import alessiovulpinari.NexusFantasiaBE.entities.classes.ClassFeature;
import alessiovulpinari.NexusFantasiaBE.entities.classes.Subclass;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_livello_sottoclasse", nullable = false)
    private UUID subclassLevelId;

    @Column(name = "numero_livello")
    private int levelNumber;

    @OneToOne
    @JoinColumn(name = "tratto_sottoclasse_id")
    // @PrimaryKeyJoinColumn(name = "tratto_sottoclasse")
    private ClassFeature classFeature;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id_sotto_classe")
    private Subclass subclass;

    public SubclassLevel(int levelNumber, ClassFeature classFeature, Subclass subclass) {
        this.levelNumber = levelNumber;
        this.classFeature = classFeature;
        this.subclass = subclass;
    }
}
