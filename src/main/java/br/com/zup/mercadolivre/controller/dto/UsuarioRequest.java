package br.com.zup.mercadolivre.controller.dto;

import br.com.zup.mercadolivre.annotation.UniqueValue;
import br.com.zup.mercadolivre.modelo.Usuario;
import br.com.zup.mercadolivre.utils.SenhaLimpa;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {
    @NotBlank @Email @UniqueValue(domainClass = Usuario.class, fieldName = "email")
    private String email;
    @NotBlank @Size(min = 6)
    private String senhaLimpa;

    public UsuarioRequest(String email, String senhaLimpa) {
        this.email = email;
        this.senhaLimpa = senhaLimpa;
    }

    public String getEmail() {
        return email;
    }

    public String getSenhaLimpa() {
        return senhaLimpa;
    }

    public Usuario toModel(){
        return new Usuario(email,new SenhaLimpa(senhaLimpa));
    }
}
