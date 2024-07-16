package alessiovulpinari.NexusFantasiaBE.entities.sheet;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tratti_background")
public class BackgroundFeature {

    @Id
    @GeneratedValue
    @Column(name = "is_tratto_background", nullable = false)
    private UUID backgroundFeatureId;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "descrizione", nullable = false)
    private String description;

    public BackgroundFeature(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
