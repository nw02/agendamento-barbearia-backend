package org.example.sistemaagendamento.services;

import org.example.sistemaagendamento.exception.ConflitoDeHorarioException;
import org.example.sistemaagendamento.exception.RecursoNaoEncontradoException;
import org.example.sistemaagendamento.models.Agendamento;
import org.example.sistemaagendamento.models.Status;
import org.example.sistemaagendamento.repositories.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {
    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    public Agendamento criarAgendamento(Agendamento agendamento){
        validarDisponibilidade(agendamento);
        validarHorario(agendamento);
        agendamento.setStatus(Status.PENDENTE);
        agendamento.setPreco(agendamento.getServico().getValor());

        return agendamentoRepository.save(agendamento);
    }

    public Agendamento alterarAgendamento(Agendamento agendamento){
        if(agendamento.getId() == null){
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        Agendamento agendamentoAtual = agendamentoRepository.findById(agendamento.getId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Agendamento não encontrado o ID: " + agendamento.getId()));

        if(agendamento.getDataHora() != null && agendamento.getPrestadorId() != null) {
            if (!agendamento.getDataHora().equals(agendamentoAtual.getDataHora()) ||
                    !agendamento.getPrestadorId().equals(agendamentoAtual.getPrestadorId())) {
                validarDisponibilidade(agendamento);
                validarHorario(agendamento);
            }
        }

        if(agendamento.getServico() != null){
            agendamentoAtual.setServico(agendamento.getServico());
            agendamentoAtual.setPreco(agendamento.getServico().getValor());
        }

        if(agendamento.getStatus() != null){ agendamentoAtual.setStatus(agendamento.getStatus()); }

        if(agendamento.getPrestadorId() != null) { agendamentoAtual.setPrestadorId(agendamento.getPrestadorId()); }

        if(agendamento.getDataHora() != null) { agendamentoAtual.setDataHora(agendamento.getDataHora()); }

        return agendamentoRepository.save(agendamentoAtual);
    }

    public void deletarAgendamento(Long id){
        if(id == null){
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        agendamentoRepository.deleteById(id);
    }

    public List<Agendamento> mostrarAgendamentos(){
        return agendamentoRepository.findAll();
    }

    private void validarDisponibilidade(Agendamento agendamento){
        if (agendamento.getDataHora().getMinute() != 0){
            throw new IllegalArgumentException("Agendamentos permitidos apenas em horas cheias (ex: 09:00, 10:00).");
        }

        boolean ocupado = agendamentoRepository.existsByPrestadorIdAndDataHora(
                agendamento.getPrestadorId(),
                agendamento.getDataHora());

        if(ocupado){
            throw new ConflitoDeHorarioException("O barbeiro selecionado ja tem agendamento nesse horário");
        }
    }

    private void validarHorario(Agendamento agendamento){
        int hora = agendamento.getDataHora().getHour();
        if (hora < 9 || hora >= 19) {
            throw new IllegalArgumentException("Horário inválido. Atendemos apenas das 09:00 às 19:00.");
        }
    }
}
