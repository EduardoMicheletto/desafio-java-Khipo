package com.api.testeKhipo.model.response;


import com.api.testeKhipo.model.entity.Pedido;
import com.api.testeKhipo.model.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor @Builder @Data @NoArgsConstructor
public class PedidoResponse {

    private Long id;

    private List<ProdutoResponse> produtos;

    private BigDecimal valorTotal;


    public static PedidoResponse buildResponse(Pedido pedido) {
        if (pedido == null) {
            return null;
        }
        return PedidoResponse.builder()
                .id(pedido.getId())
                .produtos(pedido.getProdutos().stream()
                        .map(pedidoProduto -> ProdutoResponse.buildResponse(pedidoProduto.getProduto()))
                        .toList())
                .build();
    }

    public static PedidoResponse buildResponseComListaProduto(Pedido pedido, List<Produto> listaProduto) {
        if (pedido == null) {
            return null;
        }
        return PedidoResponse.builder()
                .id(pedido.getId())
                .produtos(
                        listaProduto.stream()
                                .map(ProdutoResponse::buildResponse)
                                .toList())
                .valorTotal(
                        listaProduto.stream()
                                .map(Produto::getValor)
                                .reduce(BigDecimal.ZERO, BigDecimal::add))
                .build();
    }



}
