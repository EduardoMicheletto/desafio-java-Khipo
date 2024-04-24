package com.api.testeKhipo.controller;

import com.api.testeKhipo.controller.abstracts.CrudController;
import com.api.testeKhipo.model.response.ProdutoResponse;
import com.api.testeKhipo.model.requests.ProdutoRequest;
import com.api.testeKhipo.service.ProdutoService;
import com.api.testeKhipo.service.interfaces.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController extends CrudController<ProdutoResponse, ProdutoRequest.ProdutoCriarRequest, ProdutoRequest.ProdutoAlterarRequest> {

    @Autowired
    private ProdutoService service;


    @Override
    public ICrudService<ProdutoResponse, ProdutoRequest.ProdutoCriarRequest, ProdutoRequest.ProdutoAlterarRequest> getService() {
        return service;
    }
}
