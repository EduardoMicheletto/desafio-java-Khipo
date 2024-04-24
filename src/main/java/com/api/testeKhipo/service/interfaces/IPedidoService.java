package com.api.testeKhipo.service.interfaces;


import com.api.testeKhipo.model.response.PedidoResponse;
import com.api.testeKhipo.model.requests.PedidoRequest;

public interface IPedidoService extends ICrudService<PedidoResponse, PedidoRequest.PedidoCriarRequest, PedidoRequest.PedidoAlterarRequest> {

}
