package org.example.sistemaagendamento.services;

import org.example.sistemaagendamento.exception.RecursoNaoEncontradoException;
import org.example.sistemaagendamento.models.Prestador;
import org.example.sistemaagendamento.repositories.PrestadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestadorService {
    private final PrestadorRepository prestadorRepository;

    public PrestadorService(PrestadorRepository prestadorRepository) {
        this.prestadorRepository = prestadorRepository;
    }

    public Prestador criarPrestador(Prestador prestador){
        validarNovoCadastro(prestador);
        return prestadorRepository.save(prestador);
    }

    public Prestador alterarPrestador(Prestador prestador){
        if(prestador.getId() == null){ throw new IllegalArgumentException("ID não pode ser nulo"); }

        Prestador prestadorAlterado = prestadorRepository.findById(prestador.getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Prestador não encontrado"));

        if(prestador.getEmail() != null){ prestadorAlterado.setEmail(prestador.getEmail()); }

        if(prestador.getTelefone() != null){ prestadorAlterado.setTelefone(prestador.getTelefone()); }

        if(prestador.getNome() != null){ prestadorAlterado.setNome(prestador.getNome()); }

        validarAlteracao(prestadorAlterado);

        return prestadorRepository.save(prestadorAlterado);
    }

    public void deletarPrestador(Long id){
        if(id == null){
            throw new IllegalArgumentException("ID não pode ser nulo");
        }

        if(!prestadorRepository.existsById(id)){
            throw new IllegalArgumentException("Prestador não encontrado para exclusão");
        }
        prestadorRepository.deleteById(id);
    }

    public List<Prestador> mostrarPrestadores(){
        return prestadorRepository.findAll();
    }

    private void validarNovoCadastro(Prestador prestador) {
        if (prestadorRepository.existsByEmailOrTelefone(prestador.getEmail(), prestador.getTelefone())) {
            throw new RecursoNaoEncontradoException("E-mail ou Telefone já cadastrados.");
        }
    }

    private void validarAlteracao(Prestador prestador){
        if(prestadorRepository.existsByTelefoneAndIdNot(prestador.getTelefone(), prestador.getId()) ||
                prestadorRepository.existsByEmailAndIdNot(prestador.getEmail(), prestador.getId())){
            throw new IllegalArgumentException("Os dados informados (e-mail ou telefone) já pertencem a outro cadastro.");
        }
    }
}
