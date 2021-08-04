package br.com.zup.mercadolivre.controller.dto;

import br.com.zup.mercadolivre.modelo.Opiniao;
import br.com.zup.mercadolivre.modelo.Produto;
import br.com.zup.mercadolivre.modelo.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OpiniaoRequest {
    @NotBlank
    private String titulo;
    @NotNull
    @Min(value = 1) @Max(value = 5)
    private Integer nota;
    @NotBlank @Length(max = 500)
    private String descricao;

    public OpiniaoRequest(String titulo, Integer nota, String descricao) {
        this.titulo = titulo;
        this.nota = nota;
        this.descricao = descricao;
    }

    public Opiniao toModel(Usuario usuario, Produto produto){
        return new Opiniao(titulo,nota,descricao,usuario,produto);
    }

    @Override
    public String toString() {
        return "OpiniaoRequest{" +
                "titulo='" + titulo + '\'' +
                ", nota=" + nota +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
