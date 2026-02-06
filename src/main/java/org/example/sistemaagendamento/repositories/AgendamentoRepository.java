package org.example.sistemaagendamento.repositories;

import org.example.sistemaagendamento.models.Agendamento;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends CrudRepository<Agendamento, Long> {
    List<Agendamento> findAll();

    boolean existsByPrestadorIdAndDataHora(Long prestadorId, LocalDateTime dataHora);
}
