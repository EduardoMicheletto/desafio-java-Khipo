package com.api.testeKhipo.service.interfaces;


import com.api.testeKhipo.model.response.ProdutoResponse;
import com.api.testeKhipo.model.requests.ProdutoRequest;

public interface IProdutoService extends ICrudService<ProdutoResponse, ProdutoRequest.ProdutoCriarRequest, ProdutoRequest.ProdutoAlterarRequest> {

}
