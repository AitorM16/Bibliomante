package proyecto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.entity.Libro;
import proyecto.repository.LibroRepository;

@Service
public class LibroService {

	@Autowired
	private LibroRepository libroRepository;

	public List<Libro> findAll() {
		return libroRepository.findAll();
	}

	public Optional<Libro> findById(int id) {
		return libroRepository.findById(id);
	}
}
