package br.com.zup.mercadolivre.utils;

import br.com.zup.mercadolivre.modelo.Usuario;
import org.springframework.stereotype.Component;

@Component
public class DisparadorDeEmail {
    public void enviaEmail(Usuario usuario){
        System.out.println("Email enviado para "+ usuario.getEmail());
    }
}
