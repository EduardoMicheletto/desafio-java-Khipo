package com.api.testeKhipo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity
public class PedidoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @Column(name = "quantidade")
    private int quantidade;

    public static PedidoProduto build(Long idPedido, Long idProduto, Long quantidade) {
        if (idProduto == null || quantidade == null || idPedido == null) {
            return null;
        }
        return PedidoProduto.builder()
                .pedido(Pedido.builder().id(idPedido).build())
                .produto(Produto.builder().id(idProduto).build())
                .quantidade(quantidade.intValue())
                .build();
    }

}
