package com.api.testeKhipo.dto;


import com.api.testeKhipo.entity.Produto;
import com.api.testeKhipo.entity.enums.CategoriaProduto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor @Builder @Data @NoArgsConstructor
public class ProdutoDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private BigDecimal preco;

    @NotBlank
    private CategoriaProduto categoria;


    public static ProdutoDTO buildDTO(Produto produto) {
        if (produto == null) {
            return null;
        }
        return ProdutoDTO.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .preco(produto.getPreco())
                .categoria(produto.getCategoria())
                .build();
    }



}
