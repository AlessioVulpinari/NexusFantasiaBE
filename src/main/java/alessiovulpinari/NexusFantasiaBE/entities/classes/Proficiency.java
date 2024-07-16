package alessiovulpinari.NexusFantasiaBE.entities.classes;

import alessiovulpinari.NexusFantasiaBE.enums.ProficiencyType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "competenze")
public class Proficiency {

    @Id
    @GeneratedValue
    @Column(name = "id_competenze", nullable = false)
    private UUID proficiencyId;

    @Column(name = "tipo_competenza", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProficiencyType proficiencyType;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;


    public Proficiency(ProficiencyType proficiencyType, String name, String description) {
        this.proficiencyType = proficiencyType;
        this.name = name;
        this.description = description;
    }
}
