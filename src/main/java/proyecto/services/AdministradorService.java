package proyecto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.entity.Administrador;
import proyecto.entity.Bibliotecario;
import proyecto.entity.Usuario;
import proyecto.repository.AdministradorRepository;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository administradorRepository;
    
    @Autowired
    private BibliotecarioService bibliotecarioService;

    @Autowired
    private UsuarioService usuarioService;

    private int maxPrestamosPermitidos = 3; 

    public Bibliotecario saveBibliotecario(Bibliotecario bibliotecario) {
        return bibliotecarioService.save(bibliotecario);
    }

    public Administrador saveAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    public List<Usuario> findAllUsuarios() {
        return usuarioService.findAllUsuarios();
    }

    public void setMaxPrestamos(int max) {
        this.maxPrestamosPermitidos = max;
    }

    public int getMaxPrestamos() {
        return maxPrestamosPermitidos;
    }
}
