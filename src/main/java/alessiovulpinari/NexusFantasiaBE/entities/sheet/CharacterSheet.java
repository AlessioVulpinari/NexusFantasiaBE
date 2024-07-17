package alessiovulpinari.NexusFantasiaBE.entities.sheet;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Class;
import alessiovulpinari.NexusFantasiaBE.entities.classes.Level;
import alessiovulpinari.NexusFantasiaBE.entities.classes.Proficiency;
import alessiovulpinari.NexusFantasiaBE.entities.classes.Subclass;
import alessiovulpinari.NexusFantasiaBE.entities.equipments.Equipment;
import alessiovulpinari.NexusFantasiaBE.entities.magics.Magic;
import alessiovulpinari.NexusFantasiaBE.entities.races.Race;
import alessiovulpinari.NexusFantasiaBE.entities.races.Subrace;
import alessiovulpinari.NexusFantasiaBE.enums.AbilityScoreDistribution;
import alessiovulpinari.NexusFantasiaBE.enums.Alignment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "schede_personaggio")
public class CharacterSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_scheda_personaggio", nullable = false)
    private UUID characterSheetId;

    @Column(name = "allineamento")
    @Enumerated(value = EnumType.STRING)
    private Alignment alignment;

    @Column(name = "nome")
    private String name;

    @Column(name = "età")
    private int age;

    @Column(name = "altezza")
    private String height;

    @Column(name = "peso")
    private String weight;

    @Column(name = "colore_degli_occhi")
    private String eyeColor;

    @Column(name = "colore_dei_capelli")
    private String hairColor;

    @Column(name = "carnagione")
    private String complexion;

    @Column(name = "tratti_caratteriali")
    private String personalTraits;

    @Column(name = "ideali")
    private String ideals;

    @Column(name = "legami")
    private String bonds;

    @Column(name = "difetti")
    private String flaw;

    // ADD Distribuzione Caratteristiche
    @Column(name = "distribuzione_caratteristiche")
    private AbilityScoreDistribution abilityScoreDistribution;

    // Classi
    @ManyToMany
    @JoinTable(
            name = "schede_classi",
            joinColumns = @JoinColumn(name = "id_scheda"),
            inverseJoinColumns = @JoinColumn(name = "id_classe"))
    private Set<Class> classList;

    // Sottoclassi
    @ManyToMany
    @JoinTable(
            name = "schede_sottoclassi",
            joinColumns = @JoinColumn(name = "id_scheda"),
            inverseJoinColumns = @JoinColumn(name = "id_sotto_classe"))
    private Set<Subclass> subclassSet;

    // Livelli
    @ManyToMany
    @JoinTable(name = "schede_livelli", joinColumns = @JoinColumn(name = "id_scheda"),
    inverseJoinColumns = @JoinColumn(name = "id_livello"))
    private Set<Level> levels;

    // Set of Proficiency
    @ManyToMany
    @JoinTable(name = "schede_competenze", joinColumns = @JoinColumn(name = "id_scheda"),
    inverseJoinColumns = @JoinColumn(name = "id_competenza"))
    private Set<Proficiency> proficiencies;

    // ADD Race
    @ManyToOne
    @JoinColumn(name="id_razza")
    private Race race;

    // And Subrace
    @ManyToOne
    @JoinColumn(name="id_sotto_razza")
    private Subrace subrace;

    // ADD Background
    @ManyToOne
    @JoinColumn(name = "id_background")
    private Background background;

    // ADD Feats
    @ManyToMany
    @JoinTable(name = "schede_equipaggiamenti",
            joinColumns = @JoinColumn(name = "id_scheda"),
            inverseJoinColumns = @JoinColumn(name = "id_talento"))
    private Set<Feat> feats;

    // ADD Magic
    @ManyToMany
    @JoinTable(name = "schede_magie",
            joinColumns = @JoinColumn(name = "id_scheda"),
            inverseJoinColumns = @JoinColumn(name = "id_magia"))
    private Set<Magic> magics;

    // ADD Equipment
    @ManyToMany
    @JoinTable(name = "schede_equipaggiamenti",
            joinColumns = @JoinColumn(name = "id_scheda"),
            inverseJoinColumns = @JoinColumn(name = "id_equipaggiamento"))
    private List<Equipment> equipmentList;

    public CharacterSheet(String name) {
        this.name = name;
        this.classList = new HashSet<>();
        this.subclassSet = new HashSet<>();
        this.proficiencies = new HashSet<>();
        this.feats = new HashSet<>();
        this.magics = new HashSet<>();
        this.equipmentList = new ArrayList<>();
        this.levels = new HashSet<>();
    }
}
