package proyecto.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Usuario extends Actor {

	@OneToMany
	private List<Prestamo> prestamos;

	@ManyToMany
	private List<Evento> eventosInscritos;

	public Usuario() {
		super();
	}

	public Usuario(String nombre, String primerApellido, String segundoApellido, String correoElectronico,
			String fotografia, String telefono, String direccionPostal, List<Prestamo> prestamos,
			List<Evento> eventosInscritos) {
		super(nombre, primerApellido, segundoApellido, correoElectronico, fotografia, telefono, direccionPostal);
		this.prestamos = prestamos;
		this.eventosInscritos = eventosInscritos;
	}

	public List<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	public List<Evento> getEventosInscritos() {
		return eventosInscritos;
	}

	public void setEventosInscritos(List<Evento> eventosInscritos) {
		this.eventosInscritos = eventosInscritos;
	}
}