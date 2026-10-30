package proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.services.BibliotecarioService;

@RestController
@RequestMapping("/bibliotecarios")
public class BibliotecarioController {

    @Autowired
    private BibliotecarioService bibliotecarioService;
}
