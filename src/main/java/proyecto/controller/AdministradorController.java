package proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import proyecto.entity.Administrador;
import proyecto.entity.Bibliotecario;
import proyecto.entity.Usuario;
import proyecto.services.AdministradorService;

@RestController
@RequestMapping("/admin")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;
    
 // a. Registrar nuevo administrador y bibliotecario
    @PostMapping("/administradores")
    public ResponseEntity<Administrador> registrarAdministrador(@RequestBody Administrador administrador) {
        Administrador nuevoAdmin = administradorService.saveAdministrador(administrador);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAdmin);
    }

    @PostMapping("/bibliotecarios")
    public ResponseEntity<Bibliotecario> registrarBibliotecario(@RequestBody Bibliotecario bibliotecario) {
        Bibliotecario nuevoBiblio = administradorService.saveBibliotecario(bibliotecario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoBiblio);
    }

 // b. Consultar los usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = administradorService.findAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

 // c. Configurar el máximo de prestamos
    @PutMapping("/config/maxprestamos")
    public ResponseEntity<String> configurarMaxPrestamos(@RequestParam int max) {
        administradorService.setMaxPrestamos(max);
        return ResponseEntity.ok("Número máximo de préstamos activos actualizado correctamente a: " + max);
    }
}
