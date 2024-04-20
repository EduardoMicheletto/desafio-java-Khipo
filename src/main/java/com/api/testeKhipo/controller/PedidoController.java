package com.api.testeKhipo.controller;

import com.api.testeKhipo.controller.abstracts.CrudController;
import com.api.testeKhipo.dto.PedidoDTO;
import com.api.testeKhipo.service.PedidoService;
import com.api.testeKhipo.service.interfaces.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pedido")
public class PedidoController extends CrudController<PedidoDTO> {

    @Autowired
    private PedidoService service;


    @Override
    public ICrudService<PedidoDTO> getService() {
        return service;
    }
}
