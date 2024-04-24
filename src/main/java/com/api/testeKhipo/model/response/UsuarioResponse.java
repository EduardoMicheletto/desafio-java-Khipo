package com.api.testeKhipo.model.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @Builder
@AllArgsConstructor
public class UsuarioResponse {
    @NotBlank
    private Long id;
    private String nome;
}
