package alessiovulpinari.NexusFantasiaBE.entities.races;

import alessiovulpinari.NexusFantasiaBE.entities.AbilityScore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
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
    @JoinTable(
            name = "razze_incrementi_caratteristiche",
            joinColumns = @JoinColumn(name = "id_razza"),
            inverseJoinColumns = @JoinColumn(name = "id_caratteristica"))
    private HashMap<AbilityScore, Integer> incrementedScoreMap;

    // Lista di tratti razziali
    // Lista di linguaggi parlati
    // Lista di sotto razze
}
