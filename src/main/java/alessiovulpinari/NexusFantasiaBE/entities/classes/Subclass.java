package alessiovulpinari.NexusFantasiaBE.entities.classes;

import alessiovulpinari.NexusFantasiaBE.entities.classes.levels.SubclassLevel;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_sottoclasse", nullable = false)
    private UUID subclassId;

    @Column(name = "nome_sottoclasse", nullable = false)
    private String name;

    @Column(name = "descrizione_sottoclasse", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name="id_classe", nullable=false)
    private Class aClass;

    @OneToMany(mappedBy = "subclass")
    private Set<SubclassLevel> subClassLevels;

    public Subclass(String name, String description, Class aClass) {
        this.name = name;
        this.description = description;
        this.subClassLevels = new HashSet<>();
        this.aClass = aClass;
    }
}
