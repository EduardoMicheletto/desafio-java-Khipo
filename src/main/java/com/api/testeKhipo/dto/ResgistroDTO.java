package com.api.testeKhipo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResgistroDTO {
    private String nome;
    private String email;
    private String login;
    private String senha;
}
