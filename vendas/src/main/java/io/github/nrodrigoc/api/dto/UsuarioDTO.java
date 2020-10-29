package io.github.nrodrigoc.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter@Setter
public class UsuarioDTO {

    /* Modelo JSON:
     {
       "login": "login",
       "senha": "senha"
     }
     */

    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String login;

    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String senha;

}
