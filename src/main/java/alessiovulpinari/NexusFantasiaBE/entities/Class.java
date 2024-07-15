package alessiovulpinari.NexusFantasiaBE.entities;

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

    // ADD Set of Proficiencies (Armor, Weapons, Tools, Skills, Saving Trows)
    // ADD Set List of starting equipments

    @ManyToMany
    @JoinTable(
            name = "livelli_classe",
            joinColumns = @JoinColumn(name = "id_classe"),
            inverseJoinColumns = @JoinColumn(name = "id_livello"))
    Set<Level> classLevels;

}
