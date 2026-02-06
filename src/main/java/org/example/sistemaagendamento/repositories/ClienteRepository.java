package org.example.sistemaagendamento.repositories;

import org.example.sistemaagendamento.models.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    List<Cliente> findAll();

    boolean existsByEmailOrTelefone(String email, String telefone);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByTelefoneAndIdNot(String telefone, Long id);
}
