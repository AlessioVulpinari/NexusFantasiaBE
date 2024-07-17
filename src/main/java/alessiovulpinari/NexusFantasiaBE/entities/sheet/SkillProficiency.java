package alessiovulpinari.NexusFantasiaBE.entities.sheet;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Proficiency;
import alessiovulpinari.NexusFantasiaBE.enums.ProficiencyType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SkillProficiency extends Proficiency {

    @ManyToOne
    @JoinColumn(name = "id_caratteristica")
    private AbilityScore abilityScore;

    public SkillProficiency( String name, String description, AbilityScore abilityScore) {
        super(ProficiencyType.SKILLS, name, description);
        this.abilityScore = abilityScore;
    }
}
