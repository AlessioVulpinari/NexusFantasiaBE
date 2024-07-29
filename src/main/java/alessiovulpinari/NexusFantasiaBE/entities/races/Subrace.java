package alessiovulpinari.NexusFantasiaBE.entities.races;

import alessiovulpinari.NexusFantasiaBE.entities.sheet.AbilityScore;
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
@Table(name = "sotto_razze")
public class Subrace {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_sotto_razza", nullable = false)
    private UUID subRaceId;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "descrizione", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_race", nullable = false)
    private Race race;

    @OneToMany(mappedBy = "subrace", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<IncrementedScore> incrementedScoreMap = new HashSet<>();

    // Lista di tratti razziali
    @ManyToMany
    @JoinTable(name = "sotto_razze_tratti", joinColumns = @JoinColumn(name = "id_sotto_razza"),
            inverseJoinColumns = @JoinColumn(name = "id_tratto_razziale"))
    private Set<RacialTraits> racialTraits;

    public void addRacialTrait(RacialTraits racialTraits) {
        this.racialTraits.add(racialTraits);
    }

    public void removeRacialTrait(RacialTraits racialTraits) {
        this.racialTraits.remove(racialTraits);
    }

    // Lista di linguaggi parlati
    @ManyToMany
    @JoinTable(name = "sotto_razze_linguaggi",
            joinColumns = @JoinColumn(name = "id_sotto_razza"),
            inverseJoinColumns = @JoinColumn(name = "id_linguaggio"))
    private Set<Languages> languages;

    public void addLanguage(Languages languages) {
        this.languages.add(languages);
    }

    public void removeLanguage(Languages languages) {
        this.languages.remove(languages);
    }

    public Subrace(String name, String description, Race race) {
        this.name = name;
        this.description = description;
        this.race = race;
        this.racialTraits = new HashSet<>();
        this.languages = new HashSet<>();
    }

    public void addIncrementedScore(AbilityScore abilityScore, Integer incrementValue) {
        IncrementedScore incrementedScore = new IncrementedScore(abilityScore, incrementValue, this);
        this.incrementedScoreMap.add(incrementedScore);
    }
}
