package br.com.zup.mercadolivre.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    @NotNull @ManyToOne
    private Usuario usuario;
    @NotNull @ManyToOne
    private Produto produto;

    @Deprecated
    public Pergunta(){}

    public Pergunta(String titulo, Usuario usuario, Produto produto) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
    }
}
