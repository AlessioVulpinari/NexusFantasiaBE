package alessiovulpinari.NexusFantasiaBE.entities;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Proficiency;
import alessiovulpinari.NexusFantasiaBE.enums.ProficiencyType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "competenze_abilità")
public class SkillProficiency extends Proficiency {

    public SkillProficiency( String name, String description) {
        super(ProficiencyType.SKILLS, name, description);
    }

    @ManyToOne
    @JoinColumn(name = "id_caratteristica")
    private AbilityScore abilityScore;
}
