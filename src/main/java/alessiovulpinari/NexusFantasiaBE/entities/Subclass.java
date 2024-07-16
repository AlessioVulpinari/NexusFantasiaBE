package alessiovulpinari.NexusFantasiaBE.entities;

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
@Table(name = "sottoclassi")
public class Subclass {

    @Id
    @GeneratedValue
    @Column(name = "id_sottoclasse", nullable = false)
    private UUID subclassId;

    @Column(name = "nome_sottoclasse", nullable = false)
    private String name;

    @Column(name = "descrizione_sottoclasse", nullable = false)
    private String description;

    public Subclass(String name, String description, Class aClass) {
        this.name = name;
        this.description = description;
        this.subClassLevels = new HashSet<>();
        this.aClass = aClass;
    }

    @ManyToOne
    @JoinColumn(name="id_class", nullable=false)
    private Class aClass;

    @ManyToMany
    @JoinTable(
            name = "sottoclassi_livelli",
            joinColumns = @JoinColumn(name = "id_sottoclasse"),
            inverseJoinColumns = @JoinColumn(name = "id_livello"))
    Set<SubclassLevel> subClassLevels;
}
