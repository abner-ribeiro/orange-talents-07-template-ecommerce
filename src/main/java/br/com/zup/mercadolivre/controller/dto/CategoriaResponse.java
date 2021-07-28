package br.com.zup.mercadolivre.controller.dto;

public class CategoriaResponse {
    private Long id;
    private String nome;

    public CategoriaResponse(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
