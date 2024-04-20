package com.api.testeKhipo.dto;


import com.api.testeKhipo.entity.Pedido;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor @Builder @Data @NoArgsConstructor
public class PedidoDTO {

    private Long id;

    @NotBlank
    private UsuarioDTO usuario;

    @NotBlank
    private List<ProdutoDTO> produtos;

    private BigDecimal valorTotal;


    public static PedidoDTO buildDTO(Pedido pedido) {
        if (pedido == null) {
            return null;
        }
        return PedidoDTO.builder()
                .id(pedido.getId())
                .usuario(UsuarioDTO.builder().id(pedido.getUsuario().getId()).build())
                .produtos(pedido.getProdutos().stream().map(pedidoProduto -> ProdutoDTO.buildDTO(pedidoProduto.getProduto())).toList())
                .build();
    }



}
