package com.api.testeKhipo.service;

import com.api.testeKhipo.model.entity.Produto;
import com.api.testeKhipo.model.response.PedidoResponse;
import com.api.testeKhipo.model.entity.Pedido;
import com.api.testeKhipo.model.requests.PedidoRequest;
import com.api.testeKhipo.model.response.ProdutoResponse;
import com.api.testeKhipo.repository.PedidoRepository;
import com.api.testeKhipo.repository.ProdutoRepository;
import com.api.testeKhipo.service.interfaces.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PedidoService implements IPedidoService {

    @Autowired
    private PedidoRepository repository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private PedidoProdutoRepository pedidoProdutoRepository;

    @Override
    public List<PedidoResponse> findAll(Pageable paginacao) {
        return repository.findAll(paginacao).stream().map(PedidoResponse::buildResponse).collect(Collectors.toList());
    }

    @Override
    public PedidoResponse findById(Long id) {
        return PedidoResponse.buildResponse(repository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public PedidoResponse create(PedidoRequest.PedidoCriarRequest request) throws Exception{
        //valida se existe os produtos
        if(request.produtosIdQuantidade().entrySet().stream().anyMatch(i -> !produtoRepository.existsById(i.getKey()))){
            throw new Exception("Produto não existe no banco de dados.");
        }
        //cria o pedido
        Pedido entity = repository.save(new Pedido());

        //cria o pedido com o id, para gerar os pedidosProduto
        entity = Pedido.buildFromCriarRequest(entity, request);
        entity.setProdutos(pedidoProdutoRepository.saveAll(entity.getProdutos()));

        //busca os produtos para listagem no retorno, e cria o response utilizando a entity e a lista de produtos
        return PedidoResponse.buildResponseComListaProduto(entity,
                produtoRepository.findAllById(request.produtosIdQuantidade().keySet().stream().toList()));

    }

    @Override
    @Transactional
    public PedidoResponse alter(PedidoRequest.PedidoAlterarRequest pedido) throws Exception {
        if(!repository.existsById(pedido.id())){
            throw new Exception("Id não encontrado.");
        }
        return PedidoResponse.buildResponse(repository.save(Pedido.buildFromAlterarRequest(pedido)));
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        if(!repository.existsById(id)){
            throw new Exception("Id não encontrado.");
        }
        pedidoProdutoRepository.deleteAllByPedido_Id(id);
        repository.deleteById(id);
    }

}
