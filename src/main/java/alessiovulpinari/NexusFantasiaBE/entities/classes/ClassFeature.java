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
@Table(name = "tratti_classi")
public class ClassFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_tratto_classe" , nullable = false)
    private UUID classFeatureId;

    @Column(name = "nome_tratto_classe" , nullable = false)
    private String classFeatureName;

    @Column(name = "descrizione_tratto_classe" , nullable = false, length = 3000)
    private String classFeatureDescription;

    public ClassFeature(String classFeatureName, String classFeatureDescription) {
        this.classFeatureName = classFeatureName;
        this.classFeatureDescription = classFeatureDescription;
    }
}
