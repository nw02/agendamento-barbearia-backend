package org.example.sistemaagendamento.models;

import java.math.BigDecimal;

public enum TipoServicos {
    CABELO(new BigDecimal(35.00)),
    BARBA(new BigDecimal(30.00)),
    COMPLETO(new BigDecimal(60.00));

    private final BigDecimal valor;

    TipoServicos(BigDecimal valor){
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
