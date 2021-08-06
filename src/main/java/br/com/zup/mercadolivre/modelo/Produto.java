package br.com.zup.mercadolivre.modelo;

import br.com.zup.mercadolivre.controller.dto.CaracteristicaRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotNull @Positive
    private BigDecimal valor;
    @NotNull @PositiveOrZero
    private Integer quantidade;
    @Size(min = 3)
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
    @NotBlank @Size(max = 1000)
    private String descricao;
    @NotNull
    @ManyToOne
    private Usuario dono;
    @NotNull
    @ManyToOne
    private Categoria categoria;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<ImagemProduto> imagens = new ArrayList<>();
    @OneToMany(mappedBy = "produto")
    private List<Opiniao> opinioes = new ArrayList<>();
    @OneToMany(mappedBy = "produto")
    private List<Pergunta> perguntas = new ArrayList<>();

    public Produto(String nome, BigDecimal valor, Integer quantidade, String descricao, Categoria categoria, Usuario dono, List<CaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        caracteristicas.forEach(caracteristica -> this.caracteristicas.add(caracteristica.toModel(this)));
        this.descricao = descricao;
        this.categoria = categoria;
        this.dono = dono;
    }
    @Deprecated
    public Produto(){}

    public void associaImagens(List<String> urls){
        List<ImagemProduto> imagens = urls.stream().map(url -> new ImagemProduto(url,this)).collect(Collectors.toList());
        this.imagens.addAll(imagens);
    }
    public void addOpiniao(Opiniao opiniao){
        this.opinioes.add(opiniao);
    }

    public boolean abataEstoque(@Positive int quantidade) {
        Assert.isTrue(quantidade > 0, "A quantidade deve ser maior que zero para abater o estoque "+quantidade);

        if(quantidade <= this.quantidade) {
            this.quantidade-=quantidade;
            return true;

        }

        return false;
    }


    public Usuario getDono() {
        return dono;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Opiniao> getOpinioes() {
        return opinioes;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public List<String> getImagensUrl() {
        return imagens.stream().map(imagemProduto -> imagemProduto.getUrl()).collect(Collectors.toList());
    }

    public Set<CaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", dono=" + dono +
                ", categoria=" + categoria +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}
