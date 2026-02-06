package org.example.sistemaagendamento.controllers;

import jakarta.validation.Valid;
import org.example.sistemaagendamento.models.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.example.sistemaagendamento.services.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente criarCliente(@Valid @RequestBody Cliente cliente){
        return clienteService.criarCliente(cliente);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente alterarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        cliente.setId(id);
        return clienteService.alterarCliente(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable Long id){
        clienteService.deletarCliente(id);
    }

    @GetMapping
    public List mostrarClientes(){ return clienteService.mostrarClientes(); }
}
