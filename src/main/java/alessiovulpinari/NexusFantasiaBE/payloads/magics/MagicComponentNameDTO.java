package alessiovulpinari.NexusFantasiaBE.payloads.magics;

import jakarta.validation.constraints.NotEmpty;

public record MagicComponentNameDTO(@NotEmpty String magicComponentName) {
}
