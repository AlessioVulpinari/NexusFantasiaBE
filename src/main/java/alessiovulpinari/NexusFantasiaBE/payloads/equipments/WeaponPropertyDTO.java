package alessiovulpinari.NexusFantasiaBE.payloads.equipments;

import jakarta.validation.constraints.NotEmpty;

public record WeaponPropertyDTO(@NotEmpty(message = "Il nome della proprietà dell'arma non può essere vuoto!") String name,
                                @NotEmpty(message = "La descrizione della proprietà dell'arma non può essere vuota!")  String description) {
}
