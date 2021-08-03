package br.com.zup.mercadolivre.modelo;

import javax.persistence.*;

@Entity
public class ImagemProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    @ManyToOne
    private Produto produto;

    public ImagemProduto(String url, Produto produto) {
        this.url = url;
        this.produto = produto;
    }
    @Deprecated
    public ImagemProduto(){
    }
}
