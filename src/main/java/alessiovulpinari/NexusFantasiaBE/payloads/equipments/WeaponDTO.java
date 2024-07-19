package alessiovulpinari.NexusFantasiaBE.payloads.equipments;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record WeaponDTO(@NotEmpty(message = "Il nome dell'oggetto non può essere vuoto!") String name,
                        @NotEmpty(message = "La descrizione dell'oggetto non può essere vuoto!") String description,
                        @NotNull(message = "Il peso dell'oggetto non può essere nullo!") Double weight,
                        @NotNull(message = "Il prezzo dell'oggetto non può essere nullo!") Double cost,
                        @NotNull(message = "Il numero dei dadi danno non possono essere nulli!") Integer numberOfDamageDice,
                        @NotNull(message = "Il numero del dado danno non può essere nullo!") Integer damageDice,
                        @NotEmpty(message = "Il tipo dell'arma non può essere vuoto!") String weaponType,
                        @NotEmpty(message = "Il sotto tipo dell'arma non può essere vuoto!") String weaponSubtype,
                        @NotEmpty(message = "Il tipo di danno dell'arma non può essere vuoto!") String damageType) {
}
