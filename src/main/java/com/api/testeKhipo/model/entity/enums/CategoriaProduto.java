package com.api.testeKhipo.model.entity.enums;

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

    // Método estático para obter a categoria pelo ID
    public static CategoriaProduto get(int id) {
        for (CategoriaProduto categoria : values()) {
            if (categoria.ordinal() == id) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("ID inválido: " + id);
    }

    public static CategoriaProduto get(String value) {
        for (CategoriaProduto categoria : values()) {
            if (categoria.name().equals(value)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("value inválido: " + value);
    }

}
