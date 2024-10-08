package alessiovulpinari.NexusFantasiaBE.entities.races;

import alessiovulpinari.NexusFantasiaBE.entities.sheet.AbilityScore;
import alessiovulpinari.NexusFantasiaBE.entities.classes.Proficiency;
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
@Table(name = "razze")
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_razza", nullable = false)
    private UUID raceId;

    @Column(name = "descrizione", nullable = false, length = 3000)
    private String description;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "età", nullable = false)
    private String ageDescription;

    @Column(name = "allineamento", nullable = false)
    private String alignmentDescription;

    @Column(name = "dimensioni", nullable = false)
    private String sizeDescription;

    @Column(name = "velocità", nullable = false)
    private int speed;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<IncrementedScore> incrementedScoreMap = new HashSet<>();

    // Lista di tratti razziali
    @ManyToMany
    @JoinTable(name = "razze_tratti", joinColumns = @JoinColumn(name = "id_razza"),
    inverseJoinColumns = @JoinColumn(name = "id_tratto_razziale"))
    private Set<RacialTraits> racialTraits;

    public void addRacialTrait(RacialTraits racialTrait) {
        this.racialTraits.add(racialTrait);
    }

    public void removeRacialTrait(RacialTraits racialTrait) {
        this.racialTraits.remove(racialTrait);
    }

    // Lista di linguaggi parlati
    @ManyToMany
    @JoinTable(name = "razze_linguaggi",
            joinColumns = @JoinColumn(name = "id_razza"),
            inverseJoinColumns = @JoinColumn(name = "id_linguaggio"))
    private Set<Languages> languages;

    public void addLanguage(Languages languages) {
        this.languages.add(languages);
    }

    public void removeLanguages(Languages languages) {
        this.languages.remove(languages);
    }

    // Lista di sotto razze
    @OneToMany(mappedBy = "race")
    private Set<Subrace> subraceSet;

    public void addSubRace(Subrace subrace) {
        this.subraceSet.add(subrace);
    }

    public void removeSubRace(Subrace subrace) {
        this.subraceSet.remove(subrace);
    }

    // Lista delle competenze aggiuntive
    @ManyToMany
    @JoinTable(name = "razze_competenze", joinColumns = @JoinColumn(name = "id_razza"),
            inverseJoinColumns = @JoinColumn(name = "id_competenza") )
    private Set<Proficiency> proficiencies;

    public void addProficiency(Proficiency proficiency) {
        this.proficiencies.add(proficiency);
    }

    public void removeProficiency(Proficiency proficiency) {
        this.proficiencies.remove(proficiency);
    }

    public Race(String name ,String description, String ageDescription, String alignmentDescription, String sizeDescription, int speed) {
        this.name = name;
        this.description = description;
        this.ageDescription = ageDescription;
        this.alignmentDescription = alignmentDescription;
        this.sizeDescription = sizeDescription;
        this.speed = speed;
        this.racialTraits = new HashSet<>();
        this.languages = new HashSet<>();
        this.subraceSet = new HashSet<>();
        this.proficiencies = new HashSet<>();
    }

    public void addIncrementedScore(AbilityScore abilityScore, Integer incrementValue) {
        IncrementedScore incrementedScore = new IncrementedScore(abilityScore, incrementValue, this);
        this.incrementedScoreMap.add(incrementedScore);
    }
}
