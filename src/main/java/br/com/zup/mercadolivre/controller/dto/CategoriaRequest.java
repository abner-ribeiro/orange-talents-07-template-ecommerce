package br.com.zup.mercadolivre.controller.dto;

import br.com.zup.mercadolivre.annotation.IdExists;
import br.com.zup.mercadolivre.annotation.UniqueValue;
import br.com.zup.mercadolivre.modelo.Categoria;
import br.com.zup.mercadolivre.repository.CategoriaRepository;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {
    @NotBlank @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;
    @IdExists(domainClass = Categoria.class, fieldName = "id")
    private Long categoriaMaeId;

    public CategoriaRequest(String nome, Long categoriaMaeId) {
        this.nome = nome;
        this.categoriaMaeId = categoriaMaeId;
    }

    public Categoria toModel(CategoriaRepository categoriaRepository){
        if(categoriaMaeId != null){
            Categoria categoriaEncontrada = categoriaRepository.findById(categoriaMaeId).get();
            return new Categoria(nome,categoriaEncontrada);
        }
        return new Categoria(nome, null);
    }

    public String getNome() {
        return nome;
    }

    public Long getCategoriaMaeId() {
        return categoriaMaeId;
    }
}
