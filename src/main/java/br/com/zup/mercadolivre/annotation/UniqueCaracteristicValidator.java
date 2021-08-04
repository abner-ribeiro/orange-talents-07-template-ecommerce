package br.com.zup.mercadolivre.annotation;

import br.com.zup.mercadolivre.controller.dto.CaracteristicaRequest;
import br.com.zup.mercadolivre.controller.dto.ProdutoRequest;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueCaracteristicValidator implements ConstraintValidator<UniqueCaracteristic, ProdutoRequest> {
    @Override
    public void initialize(UniqueCaracteristic constraintAnnotation) {
    }

    @Override
    public boolean isValid(ProdutoRequest produtoRequest, ConstraintValidatorContext constraintValidatorContext) {

        return !produtoRequest.temCaracteristicasIguais();
    }
}
