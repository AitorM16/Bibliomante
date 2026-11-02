package proyecto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.entity.Libro;
import proyecto.services.LibroService;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    // 1.a Consultar libros
    @GetMapping
    public ResponseEntity<List<Libro>> listarLibros() {
        List<Libro> libros = libroService.findAll();
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    // 1.a Ver un libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable int id) {
        Optional<Libro> libro = libroService.findById(id);
        if (libro.isPresent()) {
            return new ResponseEntity<>(libro.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
 // Añadir un libro
    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro nuevo = libroService.guardar(libro);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    // Modificar un libro existente
    @PutMapping("/{id}")
    public ResponseEntity<Libro> modificarLibro(
            @PathVariable int id,
            @RequestBody Libro datosLibro) {

        Optional<Libro> libroExistente = libroService.findById(id);

        if (libroExistente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        //4a
        if (datosLibro.getEjemplaresDisponibles() == null ||
                !libroService.ejemplaresValidos(id, datosLibro.getEjemplaresDisponibles())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
       

        datosLibro.setId(id);
        Libro actualizado = libroService.guardar(datosLibro);
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
        
        
        
        
        
    }
 // Eliminar un libro si no tiene préstamos activos o vencidos
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable int id) {
        Optional<Libro> libro = libroService.findById(id);

        if (libro.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        boolean eliminado = libroService.eliminar(id);

        if (!eliminado) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
}
