package proyecto.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Administrador extends Actor {
	@OneToMany
	private List<Noticia> noticiasGestionadas;

	public Administrador() {
		super();
	}

	public Administrador(String nombre, String primerApellido, String segundoApellido, String correoElectronico,
			String fotografia, String telefono, String direccionPostal, List<Noticia> noticiasGestionadas) {
		super(nombre, primerApellido, segundoApellido, correoElectronico, fotografia, telefono, direccionPostal);
		this.noticiasGestionadas = noticiasGestionadas;
	}

	public List<Noticia> getNoticiasGestionadas() {
		return noticiasGestionadas;
	}

	public void setNoticiasGestionadas(List<Noticia> noticiasGestionadas) {
		this.noticiasGestionadas = noticiasGestionadas;
	}
}