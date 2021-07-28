package br.com.zup.mercadolivre.controller;

import br.com.zup.mercadolivre.controller.dto.CategoriaRequest;
import br.com.zup.mercadolivre.controller.dto.CategoriaResponse;
import br.com.zup.mercadolivre.modelo.Categoria;
import br.com.zup.mercadolivre.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity<CategoriaResponse> cadastrar(@RequestBody @Valid CategoriaRequest categoriaRequest){
        Categoria categoria = categoriaRequest.toModel(categoriaRepository);
        categoriaRepository.save(categoria);
        return ResponseEntity.status(HttpStatus.OK).body(new CategoriaResponse(categoria.getId(), categoria.getNome()));
    }

}
