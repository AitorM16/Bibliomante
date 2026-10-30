package proyecto.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Bibliotecario extends Actor {

	@OneToMany
	private List<Evento> eventosOrganizados;

	public Bibliotecario() {
		super();
	}

	public Bibliotecario(String nombre, String primerApellido, String segundoApellido, String correoElectronico,
			String fotografia, String telefono, String direccionPostal, List<Evento> eventosOrganizados) {
		super(nombre, primerApellido, segundoApellido, correoElectronico, fotografia, telefono, direccionPostal);
		this.eventosOrganizados = eventosOrganizados;
	}

	public List<Evento> getEventosOrganizados() {
		return eventosOrganizados;
	}

	public void setEventosOrganizados(List<Evento> eventosOrganizados) {
		this.eventosOrganizados = eventosOrganizados;
	}
}