package alessiovulpinari.NexusFantasiaBE.entities.classes;

import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.Level;
import alessiovulpinari.NexusFantasiaBE.entities.equipments.Equipment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "classi")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_classe", nullable = false)
    private UUID classId;

    @Column(name = "nome", nullable = false)
    private String className;

    @Column(name = "descrizione", length = 3000)
    private String description;

    @Column(name = "livello_per_sotto_classe")
    private int levelForSubClass;

    @Column(name = "dadi_vita", nullable = false)
    private int hitDice;

    // Set of Proficiencies (Armor, Weapons, Tools, Skills, Saving Trows)
    @ManyToMany
    @JoinTable(
            name = "classe_competenze",
            joinColumns = @JoinColumn(name = "id_classe"),
            inverseJoinColumns = @JoinColumn(name = "id_competenza"))
    private Set<Proficiency> classProficiency;

    public void addProficiency(Proficiency proficiency) {
       this.classProficiency.add(proficiency);
    }

    public void removeProficiency(Proficiency proficiency) {
        this.classProficiency.remove(proficiency);
    }

    // ADD List of starting equipments
    @ManyToMany
    @JoinTable(name = "classi_equipaggiamenti_iniziali",
    joinColumns = @JoinColumn(name = "id_classe"),
    inverseJoinColumns = @JoinColumn(name = "id_equipaggiamento"))
    private List<Equipment> equipmentList;

    public void addEquipment(Equipment equipment) {
        this.equipmentList.add(equipment);
    }

    public void removeEquipment(Equipment equipment) {
        this.equipmentList.remove(equipment);
    }

    // Sotto classi
    @OneToMany(mappedBy="aClass")
    private Set<Subclass> subclassSet;

    // Livelli della Classe
//    @JsonIgnore
    @OneToMany(mappedBy = "aClass")
    private Set<Level> classLevels;

    //TODO aggiungere la lista di magie ?

    public Class(String className, String description, int hitDice, int levelForSubClass ) {
        this.className = className;
        this.description = description;
        this.hitDice = hitDice;
        this.levelForSubClass = levelForSubClass;
        this.classProficiency = new HashSet<>();
        this.equipmentList = new ArrayList<>();
        this.subclassSet = new HashSet<>();
        this.classLevels = new HashSet<>();
    }
}
