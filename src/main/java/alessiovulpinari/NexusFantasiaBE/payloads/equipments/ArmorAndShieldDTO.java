package alessiovulpinari.NexusFantasiaBE.payloads.equipments;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ArmorAndShieldDTO(@NotEmpty(message = "Il nome dell'oggetto non può essere vuoto!") String name,
                                @NotEmpty(message = "La descrizione dell'oggetto non può essere vuoto!") String description,
                                @NotNull(message = "Il peso dell'oggetto non può essere nullo!") Double weight,
                                @NotNull(message = "Il prezzo dell'oggetto non può essere nullo!") Double cost,
                                @NotEmpty(message = "La tipologia di protezione non può essere vuota!") String protectionType,
                                @NotNull(message = "La classe armatura dell'oggetto non può essere vuota!") Integer armorClass,
                                Integer strengthRequired,
                                @NotNull(message = "Deve essere specificato se l'oggetto impone svantaggio alle prove di furtività!") Boolean stealthDisadvantage) {
}
