package alessiovulpinari.NexusFantasiaBE.entities.classes;

import alessiovulpinari.NexusFantasiaBE.entities.equipments.Equipment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "classi")
public abstract class Class {

    @Id
    @GeneratedValue
    @Column(name = "id_classe", nullable = false)
    private UUID classId;

    @Column(name = "nome", nullable = false)
    private String className;

    @Column(name = "dadi_vita", nullable = false)
    private int hitDice;

    // Set of Proficiencies (Armor, Weapons, Tools, Skills, Saving Trows)
    @ManyToMany
    @JoinTable(
            name = "classe_competenze",
            joinColumns = @JoinColumn(name = "id_classe"),
            inverseJoinColumns = @JoinColumn(name = "id_competenza"))
    private Set<Proficiency> classProficiency;

    // ADD List of starting equipments
    @ManyToMany
    @JoinTable(name = "classi_equipaggiamenti_iniziali",
    joinColumns = @JoinColumn(name = "id_classe"),
    inverseJoinColumns = @JoinColumn(name = "id_equipaggiamento"))
    private List<Equipment> equipmentList;

    @OneToMany(mappedBy="aClass")
    private Set<Subclass> subclassSet;

    @ManyToMany
    @JoinTable(
            name = "livelli_classe",
            joinColumns = @JoinColumn(name = "id_classe"),
            inverseJoinColumns = @JoinColumn(name = "id_livello"))
    private Set<Level> classLevels;

}
