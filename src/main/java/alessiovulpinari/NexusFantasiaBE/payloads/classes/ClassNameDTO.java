package alessiovulpinari.NexusFantasiaBE.payloads.classes;

import jakarta.validation.constraints.NotEmpty;

public record ClassNameDTO(@NotEmpty String className) {
}
