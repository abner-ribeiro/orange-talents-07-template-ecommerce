package br.com.zup.mercadolivre.utils;

import br.com.zup.mercadolivre.modelo.Compra;
import br.com.zup.mercadolivre.modelo.Transacao;

public interface RetornoGatewayPagamento {
    Transacao toTransacao(Compra compra);
}
