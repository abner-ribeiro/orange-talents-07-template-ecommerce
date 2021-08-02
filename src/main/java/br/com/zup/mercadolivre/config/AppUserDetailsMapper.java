package br.com.zup.mercadolivre.config;

import br.com.zup.mercadolivre.modelo.Usuario;
import br.com.zup.mercadolivre.utils.UsuarioLogado;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper{
    @Override
    public UserDetails map(Object shouldBeASystemUser) {
        return new UsuarioLogado((Usuario)shouldBeASystemUser);
    }
}
