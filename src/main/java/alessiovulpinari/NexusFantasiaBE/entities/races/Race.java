package alessiovulpinari.NexusFantasiaBE.entities.races;

import alessiovulpinari.NexusFantasiaBE.entities.sheet.AbilityScore;
import alessiovulpinari.NexusFantasiaBE.entities.classes.Proficiency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
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
    @GeneratedValue
    @Column(name = "id_razza", nullable = false)
    private UUID raceId;

    @Column(name = "età", nullable = false)
    private String ageDescription;

    @Column(name = "allineamento", nullable = false)
    private String alignmentDescription;

    @Column(name = "dimensioni", nullable = false)
    private String sizeDescription;

    @Column(name = "velocità", nullable = false)
    private int speed;

    // Lista di punti caratteristica Incrementati (HashMap)
    @ManyToMany
    @JoinTable(
            name = "razze_incrementi_caratteristiche",
            joinColumns = @JoinColumn(name = "id_razza"),
            inverseJoinColumns = @JoinColumn(name = "id_caratteristica"))
    private HashMap<AbilityScore, Integer> incrementedScoreMap;

    // Lista di tratti razziali
    @ManyToMany
    @JoinTable(name = "razze_tratti", joinColumns = @JoinColumn(name = "id_razza"),
    inverseJoinColumns = @JoinColumn(name = "id_tratto_razziale"))
    private Set<RacialTraits> racialTraits;

    // Lista di linguaggi parlati
    @ManyToMany
    @JoinTable(name = "razze_linguaggi",
            joinColumns = @JoinColumn(name = "id_razza"),
            inverseJoinColumns = @JoinColumn(name = "id_linguaggio"))
    private Set<Languages> languages;

    // Lista di sotto razze
    @OneToMany(mappedBy = "race")
    private Set<Subrace> subraceSet;

    // Lista delle competenze aggiuntive
    @ManyToMany
    @JoinTable(name = "razze_competenze", joinColumns = @JoinColumn(name = "id_razza"),
            inverseJoinColumns = @JoinColumn(name = "id_competenza") )
    private Set<Proficiency> proficiencies;

    public Race(String ageDescription, String alignmentDescription, String sizeDescription, int speed) {
        this.ageDescription = ageDescription;
        this.alignmentDescription = alignmentDescription;
        this.sizeDescription = sizeDescription;
        this.speed = speed;
        this.incrementedScoreMap = new HashMap<>();
        this.racialTraits = new HashSet<>();
        this.languages = new HashSet<>();
        this.subraceSet = new HashSet<>();
        this.proficiencies = new HashSet<>();
    }
}
