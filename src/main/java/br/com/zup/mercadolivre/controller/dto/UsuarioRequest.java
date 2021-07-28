package br.com.zup.mercadolivre.controller.dto;

import br.com.zup.mercadolivre.modelo.Usuario;
import br.com.zup.mercadolivre.utils.SenhaLimpa;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {
    @NotBlank @Email
    private String login;
    @NotBlank @Size(min = 6)
    private String senhaLimpa;

    public UsuarioRequest(String login, String senhaLimpa) {
        this.login = login;
        this.senhaLimpa = senhaLimpa;
    }

    public String getLogin() {
        return login;
    }

    public String getSenhaLimpa() {
        return senhaLimpa;
    }

    public Usuario toModel(){
        return new Usuario(login,new SenhaLimpa(senhaLimpa));
    }
}
