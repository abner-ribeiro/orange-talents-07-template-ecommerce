package br.com.zup.mercadolivre.modelo;

import br.com.zup.mercadolivre.annotation.UniqueValue;
import br.com.zup.mercadolivre.utils.SenhaLimpa;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Email @Column(unique = true)
    private String email;
    @NotBlank @Size(min = 6)
    private String senha;
    @NotNull
    private LocalDateTime dataCriacao = LocalDateTime.now();

    public Usuario(String email, SenhaLimpa senhaLimpa) {
        Assert.hasLength(email, "O email não pode ser em branco");
        Assert.notNull(email, "o email não pode ser nulo");
        Assert.notNull(senhaLimpa, "O objeto do tipo senha limpa não pode ser nulo");

        this.email = email;
        this.senha = senhaLimpa.encode();
    }
    @Deprecated
    public Usuario(){}

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getSenha() {
        return senha;
    }
}
