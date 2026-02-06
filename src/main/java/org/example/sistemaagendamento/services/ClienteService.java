package org.example.sistemaagendamento.services;

import org.example.sistemaagendamento.exception.RecursoNaoEncontradoException;
import org.example.sistemaagendamento.models.Cliente;
import org.springframework.stereotype.Service;
import org.example.sistemaagendamento.repositories.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente criarCliente(Cliente cliente){
        validarNovoCadastro(cliente);
        return clienteRepository.save(cliente);
    }

    public Cliente alterarCliente(Cliente cliente){
        if(cliente.getId() == null){ throw new IllegalArgumentException("ID não pode ser nulo"); }

        Cliente clienteAlterado = clienteRepository.findById(cliente.getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));

        if(cliente.getEmail() != null){ clienteAlterado.setEmail(cliente.getEmail()); }

        if(cliente.getTelefone() != null){ clienteAlterado.setTelefone(cliente.getTelefone()); }

        if(cliente.getNome() != null){ clienteAlterado.setNome(cliente.getNome());}

        validarAlteracao(clienteAlterado);

        return clienteRepository.save(clienteAlterado);
    }

    public void deletarCliente(Long id){
        if(id == null){
            throw new IllegalArgumentException("ID não pode ser nulo");
        }

        if(!clienteRepository.existsById(id)){
            throw new RecursoNaoEncontradoException("Cliente não encontrado para exclusão");
        }
        clienteRepository.deleteById(id);
    }

    public List mostrarClientes(){
        return clienteRepository.findAll();
    }

    private void validarNovoCadastro(Cliente cliente) {
        if (clienteRepository.existsByEmailOrTelefone(cliente.getEmail(), cliente.getTelefone())) {
            throw new IllegalArgumentException("E-mail ou Telefone já cadastrados.");
        }
    }

    private void validarAlteracao(Cliente cliente){
        if(clienteRepository.existsByTelefoneAndIdNot(cliente.getTelefone(), cliente.getId()) ||
                clienteRepository.existsByEmailAndIdNot(cliente.getEmail(), cliente.getId())){
            throw new IllegalArgumentException("Os dados informados (e-mail ou telefone) já pertencem a outro cadastro.");
        }
    }
}
