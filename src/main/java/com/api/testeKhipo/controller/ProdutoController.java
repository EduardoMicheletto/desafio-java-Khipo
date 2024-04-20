package com.api.testeKhipo.controller;

import com.api.testeKhipo.controller.abstracts.CrudController;
import com.api.testeKhipo.dto.ProdutoDTO;
import com.api.testeKhipo.service.ProdutoService;
import com.api.testeKhipo.service.interfaces.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController extends CrudController<ProdutoDTO> {

    @Autowired
    private ProdutoService service;


    @Override
    public ICrudService<ProdutoDTO> getService() {
        return service;
    }
}
