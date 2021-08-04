package br.com.zup.mercadolivre.annotation;

import br.com.zup.mercadolivre.controller.dto.CompraRequest;
import br.com.zup.mercadolivre.modelo.Produto;
import br.com.zup.mercadolivre.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Optional;

public class ExistsQuantityValidator implements ConstraintValidator<ExistsQuantity, CompraRequest> {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Override
    public boolean isValid(CompraRequest compraRequest, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Produto> possivelProduto = produtoRepository.findById(compraRequest.getProdutoId());
        if(possivelProduto.isEmpty()){
            return false;
        }
        Produto produto = possivelProduto.get();
        return produto.getQuantidade() >= compraRequest.getQuantidade() ? true : false;
    }
}
