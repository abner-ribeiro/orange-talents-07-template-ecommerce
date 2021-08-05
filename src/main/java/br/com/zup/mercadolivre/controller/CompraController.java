package br.com.zup.mercadolivre.controller;

import br.com.zup.mercadolivre.controller.dto.CompraRequest;
import br.com.zup.mercadolivre.modelo.Compra;
import br.com.zup.mercadolivre.modelo.GatewayPagamento;
import br.com.zup.mercadolivre.modelo.Produto;
import br.com.zup.mercadolivre.repository.CompraRepository;
import br.com.zup.mercadolivre.repository.ProdutoRepository;
import br.com.zup.mercadolivre.utils.DisparadorDeEmail;
import br.com.zup.mercadolivre.utils.UsuarioLogado;
import br.com.zup.mercadolivre.validacao.ErroDeFormularioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class CompraController {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private DisparadorDeEmail disparadorDeEmail;

    @PostMapping("/compras")
    @Transactional
    public ResponseEntity compraProdutoParte01(@RequestBody @Valid CompraRequest compraRequest, @AuthenticationPrincipal UsuarioLogado usuarioLogado){
        Produto produto = produtoRepository.findById(compraRequest.getProdutoId()).get();
        Optional<Compra> possivelCompra = compraRequest.toModel(produto, usuarioLogado.get());

        if(possivelCompra.isEmpty()){
            ErroDeFormularioDto erroDeFormularioDto = new ErroDeFormularioDto("Compra","Não foi possivel realizar a compra, estoque indisponível");
            return ResponseEntity.badRequest().body(erroDeFormularioDto);
        }

        Compra compra = possivelCompra.get();
        compraRepository.save(compra);
        disparadorDeEmail.enviaEmail(produto.getDono());

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(compra.getUrlRetornoGateway())).build();
    }
}
