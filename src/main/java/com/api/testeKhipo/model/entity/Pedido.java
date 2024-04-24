package com.api.testeKhipo.model.entity;


import com.api.testeKhipo.model.requests.PedidoRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@AllArgsConstructor @Builder @Data @NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
    private List<PedidoProduto> produtos;


    public static Pedido buildFromCriarRequest(Pedido pedido, PedidoRequest.PedidoCriarRequest request) {
        if (request == null) {
            return null;
        }
        pedido.setProdutos(
                request.produtosIdQuantidade().entrySet().stream()
                .map(produto-> PedidoProduto.build(pedido.getId(), produto.getKey(), produto.getValue())).toList()
        );
        return pedido;
    }

    public static Pedido buildFromAlterarRequest(PedidoRequest.PedidoAlterarRequest request) {
        if (request == null) {
            return null;
        }
        Pedido pedido = Pedido.builder()
                .id(request.id())
                .build();

        pedido.setProdutos(
                request.produtosIdQuantidade().entrySet().stream()
                        .map(produto -> PedidoProduto.build(pedido.getId(), produto.getKey(), produto.getValue())).toList()
        );

        return pedido;
    }

}
