package br.com.zup.mercadolivre.controller;

import br.com.zup.mercadolivre.controller.dto.PerguntaRequest;
import br.com.zup.mercadolivre.modelo.Pergunta;
import br.com.zup.mercadolivre.modelo.Produto;
import br.com.zup.mercadolivre.repository.PerguntaRepository;
import br.com.zup.mercadolivre.repository.ProdutoRepository;
import br.com.zup.mercadolivre.utils.DisparadorDeEmail;
import br.com.zup.mercadolivre.utils.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PerguntaController {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private PerguntaRepository perguntaRepository;
    @Autowired
    private DisparadorDeEmail disparadorDeEmail;

    @PostMapping("/produtos/{id}/perguntas")
    public ResponseEntity adicionaPerguntas(@RequestBody @Valid PerguntaRequest perguntaRequest, @PathVariable Long id, @AuthenticationPrincipal UsuarioLogado usuarioLogado){
        Optional<Produto> possivelProduto = produtoRepository.findById(id);
        //Ou seja, ele valida se o id do produto existe e se ele é mesmo o dono do produto
        if(possivelProduto.isEmpty() || !possivelProduto.get().getDono().equals(usuarioLogado.get())){
            return ResponseEntity.badRequest().build();
        }
        Produto produto = possivelProduto.get();
        Pergunta pergunta = perguntaRequest.toModel(usuarioLogado.get(), produto);
        perguntaRepository.save(pergunta);
        disparadorDeEmail.enviaEmail(produto.getDono());
        return ResponseEntity.ok().build();
    }
}
