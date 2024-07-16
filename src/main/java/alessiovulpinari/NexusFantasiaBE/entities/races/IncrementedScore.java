package alessiovulpinari.NexusFantasiaBE.entities.races;

import alessiovulpinari.NexusFantasiaBE.entities.sheet.AbilityScore;
import alessiovulpinari.NexusFantasiaBE.entities.sheet.Feat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "incrementi_caratteristiche")
public class IncrementedScore {

    @Id
    @GeneratedValue
    @Column(name = "id_incremento", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_caratteristica", nullable = false)
    private AbilityScore abilityScore;

    @Column(name = "valore_incrementato", nullable = false)
    private Integer incrementValue;

    @ManyToOne
    @JoinColumn(name = "id_sotto_razza", nullable = false)
    private Subrace subrace;

    @ManyToOne
    @JoinColumn(name = "id_razza", nullable = false)
    private Race race;

    @ManyToOne
    @JoinColumn(name = "id_talento", nullable = false)
    private Feat feat;

    public IncrementedScore(AbilityScore abilityScore, Integer incrementValue, Subrace subrace) {
        this.abilityScore = abilityScore;
        this.incrementValue = incrementValue;
        this.subrace = subrace;
    }

    public IncrementedScore(AbilityScore abilityScore, Integer incrementValue, Race race) {
        this.abilityScore = abilityScore;
        this.incrementValue = incrementValue;
        this.race = race;
    }

    public IncrementedScore(AbilityScore abilityScore, Integer incrementValue, Feat feat) {
        this.abilityScore = abilityScore;
        this.incrementValue = incrementValue;
        this.feat = feat;
    }
}