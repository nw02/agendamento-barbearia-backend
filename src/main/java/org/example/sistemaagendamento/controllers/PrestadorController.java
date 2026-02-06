package org.example.sistemaagendamento.controllers;

import jakarta.validation.Valid;
import org.example.sistemaagendamento.models.Prestador;
import org.example.sistemaagendamento.services.PrestadorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestador")
public class PrestadorController {
    private final PrestadorService prestadorService;

    public PrestadorController(PrestadorService prestadorService) {
        this.prestadorService = prestadorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prestador criarPrestador(@Valid @RequestBody Prestador prestador){
        return prestadorService.criarPrestador(prestador);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Prestador alterarPrestador(@PathVariable Long id, @RequestBody Prestador prestador){
        prestador.setId(id);
        return prestadorService.alterarPrestador(prestador);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPrestador(@PathVariable Long id){
        prestadorService.deletarPrestador(id);
    }

    @GetMapping
    public List mostrarPrestadores(){
        return prestadorService.mostrarPrestadores();
    }
}
