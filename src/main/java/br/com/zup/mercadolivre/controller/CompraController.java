package br.com.zup.mercadolivre.controller;

import br.com.zup.mercadolivre.controller.dto.CompraRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CompraController {
    @PostMapping("/compras")
    public ResponseEntity compraProdutoParte01(@RequestBody @Valid CompraRequest compraRequest){
        return ResponseEntity.ok().build();
    }
}
