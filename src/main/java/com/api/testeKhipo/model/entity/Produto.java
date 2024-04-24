package com.api.testeKhipo.model.entity;


import com.api.testeKhipo.model.entity.enums.CategoriaProduto;
import com.api.testeKhipo.model.requests.ProdutoRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@AllArgsConstructor @Builder @Data @NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "preco")
    private BigDecimal valor;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "categoria")
    private CategoriaProduto categoria;


    public static Produto buildProdutoFromCriarRequest(ProdutoRequest.ProdutoCriarRequest request) {
        if (request == null) {
            return null;
        }
        return Produto.builder()
                .nome(request.nome())
                .valor(request.valor())
                .categoria(CategoriaProduto.get(request.idCategoria().intValue()))
                .build();
    }

    public static Produto buildProdutoFromAlterarRequest(ProdutoRequest.ProdutoAlterarRequest request) {
        if (request == null) {
            return null;
        }
        return Produto.builder()
                .id(request.id())
                .nome(request.nome())
                .valor(request.valor())
                .categoria(CategoriaProduto.get(request.idCategoria().intValue()))
                .build();
    }
}
