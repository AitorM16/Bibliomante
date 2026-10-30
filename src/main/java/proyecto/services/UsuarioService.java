package proyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.entity.Usuario;
import proyecto.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario registrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
