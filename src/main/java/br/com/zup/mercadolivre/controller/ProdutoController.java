package br.com.zup.mercadolivre.controller;

import br.com.zup.mercadolivre.annotation.IdExists;
import br.com.zup.mercadolivre.controller.dto.ImagemRequest;
import br.com.zup.mercadolivre.controller.dto.OpiniaoRequest;
import br.com.zup.mercadolivre.controller.dto.ProdutoDetalheResponse;
import br.com.zup.mercadolivre.controller.dto.ProdutoRequest;
import br.com.zup.mercadolivre.modelo.ImagemProduto;
import br.com.zup.mercadolivre.modelo.Opiniao;
import br.com.zup.mercadolivre.modelo.Produto;
import br.com.zup.mercadolivre.modelo.Usuario;
import br.com.zup.mercadolivre.repository.*;
import br.com.zup.mercadolivre.utils.UploaderSimulador;
import br.com.zup.mercadolivre.utils.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UploaderSimulador uploaderSimulador;
    @Autowired
    private OpiniaoRepository opiniaoRepository;
    @Autowired
    private ImagemProdutoRepository imagemProdutoRepository;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest, @AuthenticationPrincipal UsuarioLogado usuarioLogado){
        Produto produto = produtoRequest.toModel(categoriaRepository,usuarioLogado.get());
        System.out.println(produto);
        produtoRepository.save(produto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{id}/imagens")
    public ResponseEntity adicionaImagens(@PathVariable @NotNull Long id, @Valid ImagemRequest imagemRequest, @AuthenticationPrincipal UsuarioLogado usuarioLogado){
        List<String> urls = uploaderSimulador.upload(imagemRequest.getImagens());
        Optional<Produto> possivelProduto = produtoRepository.findById(id);
        //Ou seja, ele valida se o id do produto existe e se ele é mesmo o dono do produto
        if(possivelProduto.isEmpty() || !possivelProduto.get().getDono().equals(usuarioLogado.get())){
            return ResponseEntity.badRequest().build();
        }
        Produto produto = possivelProduto.get();
        produto.associaImagens(urls);
        produtoRepository.save(produto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/opinioes")
    public ResponseEntity adicionaOpinioes(@RequestBody @Valid OpiniaoRequest opiniaoRequest,@PathVariable Long id ,@AuthenticationPrincipal UsuarioLogado usuarioLogado){
        Optional<Produto> possivelProduto = produtoRepository.findById(id);
        //Ou seja, ele valida se o id do produto existe e se ele é mesmo o dono do produto
        if(possivelProduto.isEmpty() || !possivelProduto.get().getDono().equals(usuarioLogado.get())){
            return ResponseEntity.badRequest().build();
        }
        Produto produto = possivelProduto.get();
        Opiniao opiniao = opiniaoRequest.toModel(usuarioLogado.get(), produto);
        opiniaoRepository.save(opiniao);
        produto.addOpiniao(opiniao);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDetalheResponse> detalhaProduto(@PathVariable Long id){
        Optional<Produto> possivelProduto = produtoRepository.findById(id);
        //Ou seja, ele valida se o id do produto existe e se ele é mesmo o dono do produto
        if(possivelProduto.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Produto produto = possivelProduto.get();
        return ResponseEntity.ok().body(new ProdutoDetalheResponse(produto));
    }

}
