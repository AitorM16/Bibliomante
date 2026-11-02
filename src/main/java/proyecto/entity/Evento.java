package proyecto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Evento extends DomainEntity {

	@NotBlank
	private String titulo;
	@NotBlank
	private String descripcion;

	@NotNull
	private LocalDateTime fechaHora;

	@NotNull
	@Min(1)
	private Integer aforoMaximo;

	@ManyToOne
	private Bibliotecario organizador;

	@ManyToMany
	private List<Usuario> usuariosInscritos = new ArrayList<>();

	public Evento() {
	}

	public Evento(String titulo, String descripcion, LocalDateTime fechaHora, Integer aforoMaximo,
			Bibliotecario organizador, List<Usuario> usuariosInscritos) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaHora = fechaHora;
		this.aforoMaximo = aforoMaximo;
		this.organizador = organizador;
		this.usuariosInscritos = usuariosInscritos;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Integer getAforoMaximo() {
		return aforoMaximo;
	}

	public void setAforoMaximo(Integer aforoMaximo) {
		this.aforoMaximo = aforoMaximo;
	}

	public Bibliotecario getOrganizador() {
		return organizador;
	}

	public void setOrganizador(Bibliotecario organizador) {
		this.organizador = organizador;
	}

	public List<Usuario> getUsuariosInscritos() {
		return usuariosInscritos;
	}

	public void setUsuariosInscritos(List<Usuario> usuariosInscritos) {
		this.usuariosInscritos = usuariosInscritos;
	}
}