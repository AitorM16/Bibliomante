package proyecto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.entity.Noticia;
import proyecto.repository.NoticiaRepository;

@Service
public class NoticiaService {

	@Autowired
	private NoticiaRepository noticiaRepository;

	public List<Noticia> findAll() {
		return noticiaRepository.findAll();
	}
	
	public Optional<Noticia> findNoticiaById(int id){
		return noticiaRepository.findById(id);
	}

	public Noticia create(Noticia noticia) {
		return noticiaRepository.save(noticia);
	}

	public Noticia update(int id, Noticia n) {
		Optional<Noticia> noticiaOpt = findNoticiaById(id);
		if (noticiaOpt.isPresent()) {
			Noticia noticia = noticiaOpt.get();
			noticia.setTitulo(n.getTitulo());
			noticia.setContenido(n.getContenido());
			noticia.setFechaPublicacion(n.getFechaPublicacion());
			noticia.setImagen(n.getImagen());
			noticia.setAdministrador(n.getAdministrador());
			return noticiaRepository.save(noticia);
		}
		return null;
	}

	public boolean eliminar(int id) {
		if (findNoticiaById(id)!= null) {
			noticiaRepository.deleteById(id);;
			return true;
		}
		return false;
	}
}
