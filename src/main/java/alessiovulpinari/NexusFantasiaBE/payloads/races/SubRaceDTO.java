package alessiovulpinari.NexusFantasiaBE.payloads.races;

import jakarta.validation.constraints.NotEmpty;

public record SubRaceDTO(@NotEmpty(message = "Il nome della sotto razza non può essere vuoto!") String name,
                         @NotEmpty(message = "La descrizione della sotto razza non può essere vuota!") String description,
                         @NotEmpty(message = "Il nome della razza non può essere vuoto!") String raceName) {
}
