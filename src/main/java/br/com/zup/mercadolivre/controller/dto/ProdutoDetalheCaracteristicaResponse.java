package br.com.zup.mercadolivre.controller.dto;

import br.com.zup.mercadolivre.modelo.CaracteristicaProduto;

public class ProdutoDetalheCaracteristicaResponse {
    private String nome;
    private String descricao;

    public ProdutoDetalheCaracteristicaResponse(CaracteristicaProduto caracteristicaProduto){
        this.nome = caracteristicaProduto.getNome();
        this.descricao = caracteristicaProduto.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
