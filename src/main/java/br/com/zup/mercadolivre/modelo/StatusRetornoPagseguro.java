package br.com.zup.mercadolivre.modelo;

public enum StatusRetornoPagseguro {

    SUCESSO,ERRO;

    public StatusTransacao normaliza() {
        if(this.equals(SUCESSO)) {
            return StatusTransacao.sucesso;
        }

        return StatusTransacao.erro;
    }
}
