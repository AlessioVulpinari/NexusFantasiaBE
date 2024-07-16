package alessiovulpinari.NexusFantasiaBE.entities.sheet;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Proficiency;
import alessiovulpinari.NexusFantasiaBE.entities.equipments.Equipment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Background {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_background", nullable = false)
    private UUID backgroundId;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "descrizione", nullable = false)
    private String description;

    @Column(name = "linguaggi_extra", nullable = false)
    private int extraLanguages;

    @ManyToMany
    @JoinTable(name = "background_competenze",
            joinColumns = @JoinColumn(name = "id_background"),
            inverseJoinColumns = @JoinColumn(name = "id_competenza"))
    private Set<Proficiency> proficiencies;

    @ManyToMany
    @JoinTable(name = "background_equipaggiamenti", joinColumns = @JoinColumn(name = "id_background")
            , inverseJoinColumns = @JoinColumn(name = "id_equipaggiamento"))
    private List<Equipment> equipmentList;

    @ManyToMany
    @JoinTable(name = "background_tratti", joinColumns = @JoinColumn(name = "id_background")
            , inverseJoinColumns = @JoinColumn(name = "id_tratti"))
    private Set<BackgroundFeature> backgroundFeatures;

    public Background(String name, String description, int extraLanguages) {
        this.name = name;
        this.description = description;
        this.extraLanguages = extraLanguages;
        this.proficiencies = new HashSet<>();
        this.equipmentList = new ArrayList<>();
        this.backgroundFeatures = new HashSet<>();
    }
}
