package com.api.testeKhipo.entity;


import com.api.testeKhipo.dto.ProdutoDTO;
import com.api.testeKhipo.entity.enums.CategoriaProduto;
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
    private BigDecimal preco;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "categoria")
    private CategoriaProduto categoria;


    public static Produto buildProduto(ProdutoDTO dto) {
        if (dto == null) {
            return null;
        }
        return Produto.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .preco(dto.getPreco())
                .categoria(dto.getCategoria())
                .build();
    }

}
