package br.com.zup.mercadolivre.controller.dto;

import br.com.zup.mercadolivre.annotation.ExistsQuantity;
import br.com.zup.mercadolivre.annotation.IdExists;
import br.com.zup.mercadolivre.modelo.Produto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@ExistsQuantity
public class CompraRequest {
    @NotNull @Positive
    private Integer quantidade;
    @NotNull @IdExists(domainClass = Produto.class, fieldName = "id")
    private Long produtoId;

    public CompraRequest(Integer quantidade, Long produtoId) {
        this.quantidade = quantidade;
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }
}
