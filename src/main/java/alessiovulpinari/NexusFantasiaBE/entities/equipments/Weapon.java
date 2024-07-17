package alessiovulpinari.NexusFantasiaBE.entities.equipments;

import alessiovulpinari.NexusFantasiaBE.enums.DamageType;
import alessiovulpinari.NexusFantasiaBE.enums.WeaponSubtype;
import alessiovulpinari.NexusFantasiaBE.enums.WeaponType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Weapon extends Equipment {

    @Column(name = "numero_dadi_danno", nullable = false)
    private int numberOfDamageDice;

    @Column(name = "dado_danno", nullable = false)
    private int damageDice;

    @Column(name = "tipologia_arma")
    @Enumerated(value = EnumType.STRING)
    private WeaponType weaponType;

    @Column(name = "sotto_tipologia_arma")
    @Enumerated(value = EnumType.STRING)
    private WeaponSubtype weaponSubtype;

    @Column(name = "tipo_danno")
    @Enumerated(value = EnumType.STRING)
    private DamageType damageType;

    @ManyToMany
    @JoinTable(
            name = "armi_tratti_arma",
            joinColumns = @JoinColumn(name = "id_arma"),
            inverseJoinColumns = @JoinColumn(name = "id_tratto-arma"))
    Set<WeaponProperty> weaponProperties;

    public Weapon(String name, String description, String weight, String cost, int numberOfDamageDice, int damageDice, WeaponType weaponType, WeaponSubtype weaponSubtype, DamageType damageType) {
        super(name, description, weight, cost);
        this.numberOfDamageDice = numberOfDamageDice;
        this.damageDice = damageDice;
        this.weaponType = weaponType;
        this.weaponSubtype = weaponSubtype;
        this.damageType = damageType;
        this.weaponProperties = new HashSet<>();
    }
}
