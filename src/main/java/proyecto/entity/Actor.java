package proyecto.entity;

import jakarta.validation.constraints.NotBlank;

public class Actor extends DomainEntity {
	@NotBlank
	private String nombre;
	@NotBlank
	private String primerApellido;
	private String segundoApellido;
	@NotBlank
	private String correoElectronico;
	@NotBlank
	private String fotografia;
	@NotBlank
	private String telefono;
	@NotBlank
	private String direccionPostal;

	public Actor() {
	}

	public Actor(String nombre, String primerApellido, String segundoApellido, String correoElectronico,
			String fotografia, String telefono, String direccionPostal) {
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.correoElectronico = correoElectronico;
		this.fotografia = fotografia;
		this.telefono = telefono;
		this.direccionPostal = direccionPostal;
	}

	// Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getFotografia() {
		return fotografia;
	}

	public void setFotografia(String fotografia) {
		this.fotografia = fotografia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccionPostal() {
		return direccionPostal;
	}

	public void setDireccionPostal(String direccionPostal) {
		this.direccionPostal = direccionPostal;
	}
}