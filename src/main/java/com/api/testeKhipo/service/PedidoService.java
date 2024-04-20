package com.api.testeKhipo.service;

import com.api.testeKhipo.dto.PedidoDTO;
import com.api.testeKhipo.entity.Pedido;
import com.api.testeKhipo.repository.PedidoRepository;
import com.api.testeKhipo.service.interfaces.IPedidoService;
import com.api.testeKhipo.service.interfaces.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService implements IPedidoService {

    @Autowired
    private PedidoRepository repository;

    @Override
    public List<PedidoDTO> findAll(Pageable paginacao) {
        return repository.findAll(paginacao).stream().map(PedidoDTO::buildDTO).collect(Collectors.toList());
    }

    @Override
    public PedidoDTO findById(Long id) {
        return PedidoDTO.buildDTO(repository.findById(id).orElse(null));
    }

    @Override
    public PedidoDTO criar(PedidoDTO pedido) {
        SecurityContextHolder.getContext().getAuthentication().getName();
        return PedidoDTO.buildDTO(repository.save(Pedido.buildCategoria(pedido)));
    }

    @Override
    public PedidoDTO alterar(PedidoDTO pedido) throws Exception {
        if(!repository.existsById(pedido.getId())){
            throw new Exception("Id não encontrado.");
        }
        return PedidoDTO.buildDTO(repository.save(Pedido.buildCategoria(pedido)));
    }

    @Override
    public void deletar(Long id) throws Exception {
        if(!repository.existsById(id)){
            throw new Exception("Id não encontrado.");
        }
        repository.deleteById(id);
    }

}
