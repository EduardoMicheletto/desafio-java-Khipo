package com.api.testeKhipo.model.response;


import com.api.testeKhipo.model.entity.Produto;
import com.api.testeKhipo.model.entity.enums.CategoriaProduto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor @Builder @Data @NoArgsConstructor
public class ProdutoResponse {

    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private BigDecimal valor;

    @NotBlank
    private CategoriaProduto categoria;


    public static ProdutoResponse buildResponse(Produto produto) {
        if (produto == null) {
            return null;
        }
        return ProdutoResponse.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .valor(produto.getValor())
                .categoria(produto.getCategoria())
                .build();
    }



}
