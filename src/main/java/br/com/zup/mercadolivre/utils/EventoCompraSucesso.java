package br.com.zup.mercadolivre.utils;

import br.com.zup.mercadolivre.modelo.Compra;

public interface EventoCompraSucesso {
    void processa(Compra compra);
}
