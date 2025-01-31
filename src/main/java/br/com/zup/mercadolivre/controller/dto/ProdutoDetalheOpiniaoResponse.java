package br.com.zup.mercadolivre.controller.dto;

import br.com.zup.mercadolivre.modelo.Opiniao;

public class ProdutoDetalheOpiniaoResponse {
    private String titulo;
    private String descricao;
    private Integer nota;

    public ProdutoDetalheOpiniaoResponse(Opiniao opiniao) {
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
        this.nota = opiniao.getNota();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getNota() {
        return nota;
    }
}
