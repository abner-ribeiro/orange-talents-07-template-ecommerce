package br.com.zup.mercadolivre.controller.dto;

import br.com.zup.mercadolivre.modelo.Pergunta;

import java.time.LocalDateTime;

public class ProdutoDetalhePerguntaResponse {
    private String titulo;
    private LocalDateTime dataPergunta;

    public ProdutoDetalhePerguntaResponse(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
        this.dataPergunta = pergunta.getDataCriacao();
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getDataPergunta() {
        return dataPergunta;
    }
}
