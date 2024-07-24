package alessiovulpinari.NexusFantasiaBE.payloads.sheets;

import jakarta.validation.constraints.NotEmpty;

public record AddRaceDTO(@NotEmpty(message = "Il nome della razza non può essere vuoto!") String raceName,
                         @NotEmpty(message = "Il nome della sotto razza non può essere vuoto!") String subRaceName) {
}
