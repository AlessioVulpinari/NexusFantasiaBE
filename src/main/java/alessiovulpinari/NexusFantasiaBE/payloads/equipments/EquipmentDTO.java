package alessiovulpinari.NexusFantasiaBE.payloads.equipments;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record EquipmentDTO(@NotEmpty(message = "Il nome dell'oggetto non può essere vuoto!") String name,
                           @NotEmpty(message = "La descrizione dell'oggetto non può essere vuoto!") String description,
                           @NotNull(message = "Il peso dell'oggetto non può essere nullo!") Double weight,
                           @NotNull(message = "Il prezzo dell'oggetto non può essere nullo!") Double cost) {
}
