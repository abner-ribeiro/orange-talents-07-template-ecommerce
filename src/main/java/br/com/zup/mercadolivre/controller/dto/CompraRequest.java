package br.com.zup.mercadolivre.controller.dto;

import br.com.zup.mercadolivre.annotation.ExistsQuantity;
import br.com.zup.mercadolivre.annotation.IdExists;
import br.com.zup.mercadolivre.modelo.*;
import br.com.zup.mercadolivre.repository.ProdutoRepository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;

@ExistsQuantity
public class CompraRequest {
    @NotNull @Positive
    private Integer quantidade;
    @NotNull @IdExists(domainClass = Produto.class, fieldName = "id")
    private Long produtoId;
    @NotNull
    private GatewayPagamento gatewayPagamento;

    public CompraRequest(Integer quantidade, Long produtoId) {
        this.quantidade = quantidade;
        this.produtoId = produtoId;
    }

    public Optional<Compra> toModel(Produto produto, Usuario comprador){
        boolean abateu = produto.abateEstoque(quantidade);
        if(!abateu){
            return Optional.empty();
        }
        return Optional.of(new Compra(quantidade, produto, comprador, gatewayPagamento));
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }
}
