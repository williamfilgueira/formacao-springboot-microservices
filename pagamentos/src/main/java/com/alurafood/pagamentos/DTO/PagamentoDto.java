package com.alurafood.pagamentos.DTO;

import com.alurafood.pagamentos.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PagamentoDto {

    private Long id;


    private BigDecimal valor;

    private String nome;


    private String numero;

    private String expiracao;

    private String codigo;

    private Status status;

    private Long pedidoId;

    private Long formaDePagamentoId;
}
