package org.example.sistemaagendamento.models;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Agendamento {
    @Id
    private Long id;

    @NotNull
    private TipoServicos servico;

    @NotNull
    private Long prestadorId;

    @NotNull
    private Long clienteId;

    @NotNull
    @Future(message = "O agendamento deve ser para uma data futura")
    private LocalDateTime dataHora;

    private Status status;
    private BigDecimal preco;

    public Long getId() {
        return id;
    }

    public TipoServicos getServico() {
        return servico;
    }

    public void setServico(TipoServicos servico) {
        this.servico = servico;
    }

    public Long getPrestadorId() {
        return prestadorId;
    }

    public void setPrestadorId(Long prestadorId) {
        this.prestadorId = prestadorId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
