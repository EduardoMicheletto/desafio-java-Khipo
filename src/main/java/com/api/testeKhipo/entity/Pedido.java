package com.api.testeKhipo.entity;


import com.api.testeKhipo.dto.PedidoDTO;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
    private List<PedidoProduto> produtos;


    public static Pedido buildCategoria(PedidoDTO dto) {
        if (dto == null) {
            return null;
        }
        Pedido pedido = Pedido.builder()
                .id(dto.getId())
                .usuario(Usuario.builder().id(dto.getUsuario().getId()).build())
                .build();

        pedido.setProdutos(dto.getProdutos().stream().map(produtoDTO -> PedidoProduto.buildDTO(produtoDTO, pedido)).toList());

        return pedido;
    }

}
