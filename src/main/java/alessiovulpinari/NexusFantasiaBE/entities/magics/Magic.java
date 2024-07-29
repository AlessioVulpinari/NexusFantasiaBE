package alessiovulpinari.NexusFantasiaBE.entities.magics;

import alessiovulpinari.NexusFantasiaBE.entities.classes.Class;
import alessiovulpinari.NexusFantasiaBE.entities.classes.Subclass;
import alessiovulpinari.NexusFantasiaBE.enums.MagicSchool;
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
@Table(name = "magie")
public class Magic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_magia" , nullable = false)
    private UUID magicId;

    @Column(name = "nome" , nullable = false)
    private String name;

    @Column(name = "descrizione", nullable = false)
    private String description;

    @Column(name = "livello", nullable = false)
    private int level;

    @Column(name = "scuola_magica", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MagicSchool magicSchool;

    @Column(name = "tempo_di_lancio", nullable = false)
    private String castingTime;

    @Column(name = "portata", nullable = false)
    private String range;

    @Column(name = "durata", nullable = false)
    private String duration;

    @Column(name = "descrizione_a_livelli_maggiori", nullable = false)
    private String atHigherLevels;

    @Column(name = "magia_rituale", nullable = false)
    private Boolean isRitual;

    @ManyToMany
    @JoinTable(name = "magie_componenti",
            joinColumns = @JoinColumn(name = "id_magia"),
            inverseJoinColumns = @JoinColumn(name = "id_componente"))
    private Set<MagicComponent> magicComponents;

    public void addMagicComponent(MagicComponent magicComponent) {
        this.magicComponents.add(magicComponent);
    }

    public void removeMagicComponent(MagicComponent magicComponent) {
        this.magicComponents.remove(magicComponent);
    }

    @ManyToMany
    @JoinTable(name = "magie_classi",
            joinColumns = @JoinColumn(name = "id_magia"),
            inverseJoinColumns = @JoinColumn(name = "id_classe"))
    private Set<Class> spellList;

    public void addClassToSpellList(Class aClass) {
        this.spellList.add(aClass);
    }

    public void removeClassToSpellList(Class aClass) {
        this.spellList.remove(aClass);
    }

    @ManyToMany
    @JoinTable(name = "magie_sotto_classi",
            joinColumns = @JoinColumn(name = "id_magia"),
            inverseJoinColumns = @JoinColumn(name = "id_sotto_classe"))
    private Set<Subclass> subClassSpellList;

    public void addSubClassToSpellList(Subclass aClass) {
        this.subClassSpellList.add(aClass);
    }

    public void removeSubClassToSpellList(Subclass aClass) {
        this.subClassSpellList.remove(aClass);
    }

    public Magic(String name, String description, int level, MagicSchool magicSchool, String castingTime, String range, String duration, String atHigherLevels, Boolean isRitual) {
        this.name = name;
        this.description = description;
        this.level = level;
        this.magicSchool = magicSchool;
        this.castingTime = castingTime;
        this.range = range;
        this.duration = duration;
        this.atHigherLevels = atHigherLevels;
        this.isRitual = isRitual;
        this.magicComponents = new HashSet<>();
        this.spellList = new HashSet<>();
        this.subClassSpellList = new HashSet<>();
    }
}
