package proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
