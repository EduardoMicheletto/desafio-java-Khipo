package com.api.testeKhipo.service;

import com.api.testeKhipo.model.response.ProdutoResponse;
import com.api.testeKhipo.model.entity.Produto;
import com.api.testeKhipo.model.requests.ProdutoRequest;
import com.api.testeKhipo.repository.PedidoRepository;
import com.api.testeKhipo.repository.ProdutoRepository;
import com.api.testeKhipo.service.interfaces.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService implements IProdutoService {

    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PedidoProdutoRepository pedidoProdutoRepository;

    @Override
    public List<ProdutoResponse> findAll(Pageable paginacao) {
        return repository.findAll(paginacao).stream().map(ProdutoResponse::buildResponse).collect(Collectors.toList());
    }


    @Override
    public ProdutoResponse findById(Long id) {
        return ProdutoResponse.buildResponse(repository.findById(id).orElse(null));
    }

    @Override
    public ProdutoResponse create(ProdutoRequest.ProdutoCriarRequest produto) {
        return ProdutoResponse.buildResponse(repository.save(Produto.buildProdutoFromCriarRequest(produto)));
    }

    @Override
    public ProdutoResponse alter(ProdutoRequest.ProdutoAlterarRequest request) throws Exception {
        if(!repository.existsById(request.id())){
            throw new Exception("Id não encontrado.");
        }
        return ProdutoResponse.buildResponse(repository.save(Produto.buildProdutoFromAlterarRequest(request)));
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        if(!repository.existsById(id)){
            throw new Exception("Id não encontrado.");
        }
        if(pedidoProdutoRepository.existsByProduto_id(id)){
            throw new Exception("Produto em uso, não pode ser deletado.");
        }
        repository.deleteById(id);
    }

}
