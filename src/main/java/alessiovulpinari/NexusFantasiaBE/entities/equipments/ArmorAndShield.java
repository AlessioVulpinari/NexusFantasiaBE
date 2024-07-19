package alessiovulpinari.NexusFantasiaBE.entities.equipments;

import alessiovulpinari.NexusFantasiaBE.enums.ProtectionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ArmorAndShield extends Equipment {

    @Column(name = "tipo_protezione", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProtectionType protectionType;

    @Column(name = "classe_armatura", nullable = false)
    private int armorClass;

    @Column(name = "forza_richiesta")
    private int strengthRequired;

    @Column(name = "svantaggio_su_furtivo")
    private boolean stealthDisadvantage;

    public ArmorAndShield(String name, String description, double weight, double cost, ProtectionType protectionType, int armorClass, int strengthRequired, boolean stealthDisadvantage) {
        super(name, description, weight, cost);
        this.protectionType = protectionType;
        this.armorClass = armorClass;
        this.strengthRequired = strengthRequired;
        this.stealthDisadvantage = stealthDisadvantage;
    }
}
