package proyecto.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Prestamo extends DomainEntity{
	@NotNull
	private LocalDate fechaInicio;
	@NotNull
	private LocalDate fechaPrevistaDevolucion;

	@NotNull
	private Estado estadoPrestamo;

	@ManyToOne
	private Usuario usuario;

	@ManyToOne
	private Libro libro;

	public Prestamo() {
	}

	public Prestamo(LocalDate fechaInicio, LocalDate fechaPrevistaDevolucion, Estado estadoPrestamo, Usuario usuario,
			Libro libro) {
		this.fechaInicio = fechaInicio;
		this.fechaPrevistaDevolucion = fechaPrevistaDevolucion;
		this.estadoPrestamo = estadoPrestamo;
		this.usuario = usuario;
		this.libro = libro;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaPrevistaDevolucion() {
		return fechaPrevistaDevolucion;
	}

	public void setFechaPrevistaDevolucion(LocalDate fechaPrevistaDevolucion) {
		this.fechaPrevistaDevolucion = fechaPrevistaDevolucion;
	}

	public Estado getEstado() {
		return estadoPrestamo;
	}

	public void setEstado(Estado estadoPrestamo) {
		this.estadoPrestamo = estadoPrestamo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}
}