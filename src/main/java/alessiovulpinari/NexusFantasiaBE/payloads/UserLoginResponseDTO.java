package alessiovulpinari.NexusFantasiaBE.payloads;

import jakarta.validation.constraints.NotEmpty;

public record UserLoginResponseDTO(@NotEmpty String tokenId) {
}
