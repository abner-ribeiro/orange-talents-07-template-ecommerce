package br.com.zup.mercadolivre.controller.dto;

import java.time.LocalDateTime;

public class UsuarioResponse {
    private String email;
    private LocalDateTime dataCriacao;

    public UsuarioResponse(String email, LocalDateTime dataCriacao) {
        this.email = email;
        this.dataCriacao = dataCriacao;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
