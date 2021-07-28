package br.com.zup.mercadolivre.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Column(unique = true)
    private String nome;
    @ManyToOne
    private Categoria categoria;

    public Categoria(String nome, Categoria categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }
    @Deprecated
    public Categoria(){}

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }
}
