package proyecto.controller.dto;

import java.time.LocalDateTime;

import proyecto.entity.Evento;

public record EventoDisponibleResponse(
		int id,
		String titulo,
		String descripcion,
		LocalDateTime fechaHora,
		int aforoMaximo,
		int plazasDisponibles) {

	public static EventoDisponibleResponse from(Evento evento) {
		int inscritos = evento.getUsuariosInscritos() == null ? 0 : evento.getUsuariosInscritos().size();
		return new EventoDisponibleResponse(
				evento.getId(),
				evento.getTitulo(),
				evento.getDescripcion(),
				evento.getFechaHora(),
				evento.getAforoMaximo(),
				evento.getAforoMaximo() - inscritos);
	}
}
