package br.com.zup.mercadolivre.modelo;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Opiniao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotNull
    @Min(value = 1) @Max(value = 5)
    private Integer nota;
    @NotBlank @Length(max = 500)
    private String descricao;
    @NotNull
    @ManyToOne
    private Usuario usuario;
    @NotNull
    @ManyToOne
    private Produto produto;

    public Opiniao(String titulo, Integer nota, String descricao, Usuario usuario, Produto produto) {
        this.titulo = titulo;
        this.nota = nota;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    @Deprecated
    public Opiniao(){}

    @Override
    public String toString() {
        return "Opiniao{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", nota=" + nota +
                ", descricao='" + descricao + '\'' +
                ", usuario=" + usuario +
                ", produto=" + produto +
                '}';
    }
}
