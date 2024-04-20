package com.api.testeKhipo.service;

import com.api.testeKhipo.dto.ProdutoDTO;
import com.api.testeKhipo.entity.Produto;
import com.api.testeKhipo.repository.ProdutoRepository;
import com.api.testeKhipo.service.interfaces.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService implements IProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Override
    public List<ProdutoDTO> findAll(Pageable paginacao) {
        return repository.findAll(paginacao).stream().map(ProdutoDTO::buildDTO).collect(Collectors.toList());
    }


    @Override
    public ProdutoDTO findById(Long id) {
        return ProdutoDTO.buildDTO(repository.findById(id).orElse(null));
    }

    @Override
    public ProdutoDTO criar(ProdutoDTO produto) {
        return ProdutoDTO.buildDTO(repository.save(Produto.buildProduto(produto)));
    }

    @Override
    public ProdutoDTO alterar(ProdutoDTO produto) throws Exception {
        if(!repository.existsById(produto.getId())){
            throw new Exception("Id não encontrado.");
        }
        return ProdutoDTO.buildDTO(repository.save(Produto.buildProduto(produto)));
    }

    @Override
    public void deletar(Long id) throws Exception {
        if(!repository.existsById(id)){
            throw new Exception("Id não encontrado.");
        }
        repository.deleteById(id);
    }

}
