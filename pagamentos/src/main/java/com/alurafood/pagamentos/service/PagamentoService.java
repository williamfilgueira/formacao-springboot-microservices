package com.alurafood.pagamentos.service;

import com.alurafood.pagamentos.DTO.PagamentoDto;
import com.alurafood.pagamentos.model.Pagamento;
import com.alurafood.pagamentos.model.Status;
import com.alurafood.pagamentos.repository.PagamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class PagamentoService {


    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PagamentoDto> buscarTodosOsPagamentos(Pageable pag){
        return pagamentoRepository.findAll(pag).map( i -> modelMapper.map(i, PagamentoDto.class));
    }

    public PagamentoDto buscaPagamentoPorId(Long id){
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto criarPagamento(PagamentoDto pagamento){
        Pagamento novoPagamento = modelMapper.map(pagamento, Pagamento.class);
        novoPagamento.setStatus(Status.CRIADO);
        pagamentoRepository.save(novoPagamento);

        return modelMapper.map(novoPagamento, PagamentoDto.class);
    }

    public PagamentoDto atualizaPagamento(Long id, PagamentoDto record){
        Pagamento novoPagamento = modelMapper.map(record, Pagamento.class);
        novoPagamento.setId(id);
        novoPagamento = pagamentoRepository.save(novoPagamento);
        pagamentoRepository.save(novoPagamento);

        return modelMapper.map(novoPagamento, PagamentoDto.class);
    }

    public void excluirPagamento(Long id){
        pagamentoRepository.deleteById(id);

    }


}
