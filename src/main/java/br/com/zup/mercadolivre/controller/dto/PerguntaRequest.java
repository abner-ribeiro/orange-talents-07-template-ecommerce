package br.com.zup.mercadolivre.controller.dto;

import br.com.zup.mercadolivre.modelo.Pergunta;
import br.com.zup.mercadolivre.modelo.Produto;
import br.com.zup.mercadolivre.modelo.Usuario;

import javax.validation.constraints.NotBlank;

public class PerguntaRequest {
    @NotBlank
    private String titulo;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toModel(Usuario usuario, Produto produto){
        return new Pergunta(titulo,usuario,produto);
    }
}
