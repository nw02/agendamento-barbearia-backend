package org.example.sistemaagendamento.models;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

public class Cliente {
    @Id
    private Long id;
    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String telefone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
