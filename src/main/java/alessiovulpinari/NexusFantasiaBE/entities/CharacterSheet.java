package alessiovulpinari.NexusFantasiaBE.entities;

import alessiovulpinari.NexusFantasiaBE.enums.Alignment;
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
@Table(name = "schede_personaggio")
public class CharacterSheet {

    @Id
    @GeneratedValue
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

    @ManyToMany
    @JoinTable(
            name = "schede_classi",
            joinColumns = @JoinColumn(name = "id_scheda"),
            inverseJoinColumns = @JoinColumn(name = "id_classe"))
    Set<Class> classList;

    @ManyToMany
    @JoinTable(
            name = "schede_sottoclassi",
            joinColumns = @JoinColumn(name = "id_scheda"),
            inverseJoinColumns = @JoinColumn(name = "id_sotto_classe"))
    Set<Subclass> subclassSet;

    // Set of Proficiency ?

    // ADD Race and Subrace
    // ADD Distribuzione Caratteristiche
    // ADD Background
    // ADD Feats
    // ADD Magic
    // ADD Equipment


}
