package proyecto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
public class Libro extends DomainEntity {


    @NotBlank private String titulo;

    @ElementCollection
    private List<String> autores;

    @NotBlank private String generoLiterario;
    @NotBlank private String idioma;
    @NotBlank private String isbn;

    @NotNull
    @Min(0)
    private Integer ejemplaresDisponibles;

    private String imagenPortada; // Opcional

    @OneToMany(mappedBy = "libro")
    @JsonIgnore
    private List<Prestamo> prestamos;

    public Libro() {
    }

    public Libro(String titulo, List<String> autores, String generoLiterario, String idioma, String isbn, Integer ejemplaresDisponibles, String imagenPortada, List<Prestamo> prestamos) {
        this.titulo = titulo;
        this.autores = autores;
        this.generoLiterario = generoLiterario;
        this.idioma = idioma;
        this.isbn = isbn;
        this.ejemplaresDisponibles = ejemplaresDisponibles;
        this.imagenPortada = imagenPortada;
        this.prestamos = prestamos;
    }

   
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public List<String> getAutores() { return autores; }
    public void setAutores(List<String> autores) { this.autores = autores; }

    public String getGeneroLiterario() { return generoLiterario; }
    public void setGeneroLiterario(String generoLiterario) { this.generoLiterario = generoLiterario; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Integer getEjemplaresDisponibles() { return ejemplaresDisponibles; }
    public void setEjemplaresDisponibles(Integer ejemplaresDisponibles) { this.ejemplaresDisponibles = ejemplaresDisponibles; }

    public String getImagenPortada() { return imagenPortada; }
    public void setImagenPortada(String imagenPortada) { this.imagenPortada = imagenPortada; }

    public List<Prestamo> getPrestamos() { return prestamos; }
    public void setPrestamos(List<Prestamo> prestamos) { this.prestamos = prestamos; }
}