package alessiovulpinari.NexusFantasiaBE.entities.races;

import alessiovulpinari.NexusFantasiaBE.entities.sheet.AbilityScore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "sotto_razze")
public class Subrace {

    @Id
    @GeneratedValue
    @Column(name = "id_sotto_razza", nullable = false)
    private UUID subRaceId;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "descrizione", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_race", nullable = false)
    private Race race;

    // Lista di punti caratteristica Incrementati (HashMap)
    @ManyToMany
    @JoinTable(
            name = "sotto_razze_incrementi_caratteristiche",
            joinColumns = @JoinColumn(name = "id_sotto_razza"),
            inverseJoinColumns = @JoinColumn(name = "id_caratteristica"))
    private HashMap<AbilityScore, Integer> incrementedScoreMap;

    // Lista di tratti razziali
    @ManyToMany
    @JoinTable(name = "sotto_razze_tratti", joinColumns = @JoinColumn(name = "id_sotto_razza"),
            inverseJoinColumns = @JoinColumn(name = "id_tratto_razziale"))
    private Set<RacialTraits> racialTraits;

    // Lista di linguaggi parlati
    @ManyToMany
    @JoinTable(name = "sotto_razze_linguaggi",
            joinColumns = @JoinColumn(name = "id_sotto_razza"),
            inverseJoinColumns = @JoinColumn(name = "id_linguaggio"))
    private Set<Languages> languages;
}
