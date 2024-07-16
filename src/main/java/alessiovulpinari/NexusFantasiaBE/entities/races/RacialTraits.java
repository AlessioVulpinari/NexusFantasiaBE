package alessiovulpinari.NexusFantasiaBE.entities.races;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tratti_razziali")
public class RacialTraits {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_tratto_razziale", nullable = false)
    private UUID racialTraitId;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "descrizione", nullable = false)
    private String description;

    public RacialTraits(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
