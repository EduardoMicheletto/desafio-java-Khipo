package com.api.testeKhipo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @Builder
@AllArgsConstructor
public class UsuarioDTO {
    @NotBlank
    private Long id;
    private String nome;

}
