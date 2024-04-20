package com.api.testeKhipo.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {

    private String login;
    private String senha;

}
