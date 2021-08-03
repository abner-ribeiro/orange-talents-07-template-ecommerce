package br.com.zup.mercadolivre.controller;

import br.com.zup.mercadolivre.annotation.IdExists;
import br.com.zup.mercadolivre.controller.dto.ImagemRequest;
import br.com.zup.mercadolivre.controller.dto.ProdutoRequest;
import br.com.zup.mercadolivre.modelo.ImagemProduto;
import br.com.zup.mercadolivre.modelo.Produto;
import br.com.zup.mercadolivre.modelo.Usuario;
import br.com.zup.mercadolivre.repository.CategoriaRepository;
import br.com.zup.mercadolivre.repository.ImagemProdutoRepository;
import br.com.zup.mercadolivre.repository.ProdutoRepository;
import br.com.zup.mercadolivre.repository.UsuarioRepository;
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

}
