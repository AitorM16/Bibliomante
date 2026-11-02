package proyecto.controller.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record SolicitarPrestamoRequest(
		@NotNull Integer usuarioId,
		@NotNull Integer libroId,
		@NotNull LocalDate fechaPrevistaDevolucion) {
}
