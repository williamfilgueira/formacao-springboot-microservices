package com.alurafood.pagamentos.controller;

import com.alurafood.pagamentos.DTO.PagamentoDto;
import com.alurafood.pagamentos.service.PagamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;


@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public Page<PagamentoDto> listarTodosOsPagamentos(@PageableDefault(size = 10) Pageable paginacao){

        return pagamentoService.buscarTodosOsPagamentos(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDto> listaPagamentoPorId(@PathVariable @NotNull Long id){
        PagamentoDto record = pagamentoService.buscaPagamentoPorId(id);
        return ResponseEntity.ok(record);
    }

    @PostMapping
    public ResponseEntity<PagamentoDto> cadastrarPagamento(@RequestBody @Valid PagamentoDto record, UriComponentsBuilder uriBuilder){
        PagamentoDto novoPagamento = pagamentoService.criarPagamento(record);

        URI endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(novoPagamento.getId()).toUri();

        return ResponseEntity.created(endereco).body(novoPagamento);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDto> atualizarPagamento(@PathVariable @NotNull Long id, @RequestBody @Valid PagamentoDto record){
        PagamentoDto pagamentoAtualizado = pagamentoService.atualizaPagamento(id, record);
        return ResponseEntity.ok(pagamentoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PagamentoDto> deletarPagamento(@PathVariable @NotNull Long id){
        pagamentoService.excluirPagamento(id);
        return ResponseEntity.noContent().build();
    }

}
