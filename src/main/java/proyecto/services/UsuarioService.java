package proyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.entity.Usuario;
import proyecto.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario registrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

	/**
	 * Edita los datos personales de un usuario.
	 * @author Carlos Moran "carlos@carlosmoran.dev"
	 * @param id ID del usuario a modificar
	 * @param editUsuario Objeto Usuario con los nuevos datos.
	 * @return True - El usuario ha sido modificado. | False - Ha ocurrido un error.
	 */
	public boolean editarUsuario(int id, Usuario editUsuario) {
		Optional<Usuario> optUsuario = usuarioRepository.findById(id);
		if(optUsuario.isEmpty()) return false;
		Usuario u = optUsuario.get();
		u.setNombre(editUsuario.getNombre());
		u.setPrimerApellido(editUsuario.getPrimerApellido());
		u.setSegundoApellido(editUsuario.getSegundoApellido());
		u.setCorreoElectronico(editUsuario.getCorreoElectronico());
		u.setFotografia(editUsuario.getFotografia());
		u.setTelefono(editUsuario.getTelefono());
		u.setDireccionPostal(editUsuario.getDireccionPostal());
		return true;
	}

	/** Exportar datos.
	 * Devuelve el objeto Usuario.
	 * @author Carlos Moran "carlos@carlosmoran.dev"
	 * @param id ID del usuario a exportar datos
	 * @return Objeto Usuario con todos los datos
	 */
	public Usuario exportarDatos(int id) {
		Optional<Usuario> optUsuario = usuarioRepository.findById(id);
        return optUsuario.orElse(null);
    }
}
