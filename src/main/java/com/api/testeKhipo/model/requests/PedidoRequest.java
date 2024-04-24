package com.api.testeKhipo.model.requests;

import java.util.HashMap;

public class PedidoRequest {

    //Para receber request de criacao, não tem id, o map de produtos deve ser o Codigo do produto seguido pela quantidade
    public record PedidoCriarRequest(HashMap<Long, Long> produtosIdQuantidade) {
    }


    //Para receber os requests de alteracao, o map de produtos deve ser o Codigo do produto seguido pela quantidade
    public record PedidoAlterarRequest(Long id, HashMap<Long, Long> produtosIdQuantidade) {
    }



}