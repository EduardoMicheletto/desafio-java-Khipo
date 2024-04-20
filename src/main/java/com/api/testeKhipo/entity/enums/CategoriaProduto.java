package com.api.testeKhipo.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public enum CategoriaProduto {
    BEBIDA(0, "Bebida"),
    ENTRADA(1, "Entrada"),
    PRATO_PRINCIPAL(2, "Prato Principal"),
    SOBREMESA(3, "Sobremesa");

    CategoriaProduto(int id, String descricao) {
    }
}
