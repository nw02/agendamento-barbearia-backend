package org.example.sistemaagendamento.controllers;

import jakarta.validation.Valid;
import org.example.sistemaagendamento.models.Agendamento;
import org.example.sistemaagendamento.services.AgendamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {
    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Agendamento criarAgendamento(@Valid @RequestBody Agendamento agendamento){
        return agendamentoService.criarAgendamento(agendamento);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Agendamento alterarAgendamento(@PathVariable Long id, @RequestBody Agendamento agendamento){
        agendamento.setId(id);
        return agendamentoService.alterarAgendamento(agendamento);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAgendamento(@PathVariable Long id){
        agendamentoService.deletarAgendamento(id);
    }

    @GetMapping
    public List<Agendamento> mostrarAgendamentos(){
        return agendamentoService.mostrarAgendamentos();
    }
}
