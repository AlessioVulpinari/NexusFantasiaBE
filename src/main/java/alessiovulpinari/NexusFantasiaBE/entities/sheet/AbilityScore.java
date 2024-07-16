package alessiovulpinari.NexusFantasiaBE.entities.sheet;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "punti_caratteristica")
public class AbilityScore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_caratteristica", nullable = false)
    private UUID abilityScoreId;

    @Column(name = "nome_caratteristica", nullable = false)
    private String name;

    @Column(name = "descrizione_caratteristica", nullable = false)
    private String description;

    @OneToMany(mappedBy = "abilityScore")
    private Set<SkillProficiency> proficiencySet;
}
