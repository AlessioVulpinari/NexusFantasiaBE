package alessiovulpinari.NexusFantasiaBE.payloads;

import jakarta.validation.constraints.NotBlank;

public record UserRoleDTO(@NotBlank String name) {
}
