package proyecto.services;

import java.util.List;

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
}
