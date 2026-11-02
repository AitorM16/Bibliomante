package proyecto.controller.dto;

import jakarta.validation.constraints.NotNull;

public record InscripcionEventoRequest(@NotNull Integer usuarioId) {
}
