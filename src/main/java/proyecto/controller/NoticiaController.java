package proyecto.controller;

import java.util.List;

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

import proyecto.entity.Noticia;
import proyecto.services.NoticiaService;

@RestController
@RequestMapping("/noticias")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;
    
 // 1.d Consultar las noticias
    @GetMapping
    public ResponseEntity<List<Noticia>> listarNoticias() {
        List<Noticia> noticias = noticiaService.findAll();
        return new ResponseEntity<>(noticias, HttpStatus.OK);
    }
    

// 5 d. Gestiones de las noticias
    @PostMapping
    public ResponseEntity<Noticia> crearNoticia(@RequestBody Noticia noticia) {
        Noticia nueva = noticiaService.create(noticia);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Noticia> modificarNoticia(@PathVariable int id, @RequestBody Noticia noticia) {
        Noticia nuevaNoticia = noticiaService.update(id, noticia);
        if (nuevaNoticia != null) {
            return ResponseEntity.ok(nuevaNoticia);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNoticia(@PathVariable int id) {
        if (noticiaService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
