package br.com.zup.mercadolivre.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

public class SenhaLimpa {
    private String senha;

    public SenhaLimpa(String senha) {
        Assert.isTrue(senha.length() >= 6, "senha tem que ter no minimo 6 caracteres");
        this.senha = senha;
    }

    public String encode() {
        return new BCryptPasswordEncoder().encode(senha);
    }
}
