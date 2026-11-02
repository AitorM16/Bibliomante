package proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import proyecto.entity.Usuario;
import proyecto.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
 // 1.c Registrarse
    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrarse(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/exportar/{idUsuario}")
    public ResponseEntity<Usuario> exportarUsuario(@PathVariable Integer idUsuario) {
        Usuario exportado = usuarioService.exportarDatos(idUsuario);
        if(exportado == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(exportado);
    }

    @PutMapping("/editar/{idUsuario}")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable Integer idUsuario, @RequestBody Usuario usuario) {
        boolean resultado = usuarioService.editarUsuario(idUsuario, usuario);
        if(resultado) return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
