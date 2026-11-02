package proyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.entity.Bibliotecario;
import proyecto.repository.BibliotecarioRepository;

@Service
public class BibliotecarioService {

    @Autowired
    private BibliotecarioRepository bibliotecarioRepository;


    public Bibliotecario save(Bibliotecario bibliotecario) {
        return bibliotecarioRepository.save(bibliotecario);
    }
}
