package br.com.zup.mercadolivre.controller;

import br.com.zup.mercadolivre.controller.dto.UsuarioRequest;
import br.com.zup.mercadolivre.controller.dto.UsuarioResponse;
import br.com.zup.mercadolivre.modelo.Usuario;
import br.com.zup.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody @Valid UsuarioRequest usuarioRequest){
        Usuario usuario = usuarioRequest.toModel();
        usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(new UsuarioResponse(usuario.getEmail(),usuario.getDataCriacao()));
    }
}
