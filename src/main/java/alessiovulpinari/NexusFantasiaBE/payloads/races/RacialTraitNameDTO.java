package alessiovulpinari.NexusFantasiaBE.payloads.races;

import jakarta.validation.constraints.NotEmpty;

public record RacialTraitNameDTO(@NotEmpty String racialTraitName) {
}
