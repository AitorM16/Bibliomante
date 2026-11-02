package proyecto.controller.dto;

import java.time.LocalDate;

import proyecto.entity.Estado;
import proyecto.entity.Prestamo;

public record PrestamoResponse(
		int id,
		int usuarioId,
		int libroId,
		String tituloLibro,
		LocalDate fechaInicio,
		LocalDate fechaPrevistaDevolucion,
		Estado estado) {

	public static PrestamoResponse from(Prestamo prestamo) {
		return new PrestamoResponse(
				prestamo.getId(),
				prestamo.getUsuario().getId(),
				prestamo.getLibro().getId(),
				prestamo.getLibro().getTitulo(),
				prestamo.getFechaInicio(),
				prestamo.getFechaPrevistaDevolucion(),
				prestamo.getEstado());
	}
}
