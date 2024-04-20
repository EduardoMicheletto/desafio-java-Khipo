package com.api.testeKhipo.entity;

import com.api.testeKhipo.dto.ProdutoDTO;
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


    public static PedidoProduto buildDTO(ProdutoDTO produto, Pedido pedido) {
        if (produto == null) {
            return null;
        }
        return PedidoProduto.builder()
                .pedido(pedido)
                .produto(Produto.buildProduto(produto))
                .build();
    }

}
