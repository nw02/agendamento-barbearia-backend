package org.example.sistemaagendamento.repositories;

import org.example.sistemaagendamento.models.Prestador;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrestadorRepository extends CrudRepository<Prestador, Long> {
    List<Prestador> findAll();

    boolean existsByEmailOrTelefone(String email, String telefone);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByTelefoneAndIdNot(String telefone, Long id);
}
