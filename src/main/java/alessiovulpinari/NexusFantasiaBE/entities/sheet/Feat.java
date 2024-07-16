package alessiovulpinari.NexusFantasiaBE.entities.sheet;

import alessiovulpinari.NexusFantasiaBE.entities.races.IncrementedScore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "talenti")
public class Feat {

    @Id
    @GeneratedValue
    @Column(name = "id_talento", nullable = false)
    private UUID featId;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "prerequisito", nullable = false)
    private String prerequisite;

    @Column(name = "descrizione", nullable = false)
    private String description;

    @OneToMany(mappedBy = "feat", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<IncrementedScore> incrementedScoreMap = new HashSet<>();

    public Feat(String name, String prerequisite, String description) {
        this.name = name;
        this.prerequisite = prerequisite;
        this.description = description;
    }
}
