package br.com.zup.mercadolivre.controller.dto;

import br.com.zup.mercadolivre.modelo.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoDetalheResponse {
    private String nome;
    private BigDecimal preco;
    private String descricao;
    private Set<ProdutoDetalheCaracteristicaResponse> caracteristicas;
    private List<String> urlImagens;
    private List<ProdutoDetalheOpiniaoResponse> opinioes;
    private List<ProdutoDetalhePerguntaResponse> perguntas;
    private Integer numeroDeNotas;
    private Double mediaDeNotas;


    public ProdutoDetalheResponse(Produto produto){
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getValor();
        this.caracteristicas = produto.getCaracteristicas().stream().map(ProdutoDetalheCaracteristicaResponse::new).collect(Collectors.toSet());
        this.urlImagens = produto.getImagensUrl();
        this.opinioes = produto.getOpinioes().stream().map(ProdutoDetalheOpiniaoResponse::new).collect(Collectors.toList());
        this.perguntas = produto.getPerguntas().stream().map(ProdutoDetalhePerguntaResponse ::new).collect(Collectors.toList());
        List<Integer> notas = produto.getOpinioes().stream().map( opiniao -> opiniao.getNota()).collect(Collectors.toList());
        this.numeroDeNotas = notas.size();
        OptionalDouble possiveisMedias = notas.stream().mapToInt(nota -> nota).average();
        this.mediaDeNotas = possiveisMedias.orElseGet(() -> 0.0);
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getNumeroDeNotas() {
        return numeroDeNotas;
    }

    public Double getMediaDeNotas() {
        return mediaDeNotas;
    }

    public Set<ProdutoDetalheCaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public List<String> getUrlImagens() {
        return urlImagens;
    }

    public List<ProdutoDetalheOpiniaoResponse> getOpinioes() {
        return opinioes;
    }

    public List<ProdutoDetalhePerguntaResponse> getPerguntas() {
        return perguntas;
    }
}
