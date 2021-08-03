package br.com.zup.mercadolivre.controller.dto;

import br.com.zup.mercadolivre.annotation.IdExists;
import br.com.zup.mercadolivre.annotation.UniqueCaracteristic;
import br.com.zup.mercadolivre.modelo.CaracteristicaProduto;
import br.com.zup.mercadolivre.modelo.Categoria;
import br.com.zup.mercadolivre.modelo.Produto;
import br.com.zup.mercadolivre.modelo.Usuario;
import br.com.zup.mercadolivre.repository.CategoriaRepository;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@UniqueCaracteristic
public class ProdutoRequest {
    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull @PositiveOrZero
    private Integer quantidade;
    @Size(min = 3)
    @Valid
    private List<CaracteristicaRequest> caracteristicas = new ArrayList<>();
    @NotBlank @Size(max = 1000)
    private String descricao;
    @NotNull @IdExists(domainClass = Categoria.class, fieldName = "id")
    private Long categoriaId;

    public ProdutoRequest(String nome, BigDecimal valor, Integer quantidade, String descricao, Long categoriaId, List<CaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
        this.caracteristicas.addAll(caracteristicas);
    }

    public List<CaracteristicaRequest> getCaracteristicas(){
        return caracteristicas;
    }

    public boolean temCaracteristicasIguais(){
        List<String> caracteristicasNomesCompara = new ArrayList<>();
        for(CaracteristicaRequest caracteristica : caracteristicas){
            if(caracteristicasNomesCompara.contains(caracteristica.getNome())){
                return true;
            }
            caracteristicasNomesCompara.add(caracteristica.getNome());
        }
        return false;
    }


    public Produto toModel(CategoriaRepository categoriaRepository, Usuario usuario){
        Categoria categoria = categoriaRepository.findById(categoriaId).get();
        return new Produto(nome,valor,quantidade,descricao,categoria,usuario,caracteristicas);
    }

    @Override
    public String toString() {
        return "ProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", caracteristicas=" + caracteristicas +
                ", descricao='" + descricao + '\'' +
                ", categoriaId=" + categoriaId +
                '}';
    }
}
