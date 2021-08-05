package br.com.zup.mercadolivre.modelo;

import br.com.zup.mercadolivre.annotation.IdExists;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Positive
    private Integer quantidade;
    @NotNull @ManyToOne
    private Produto produto;
    @ManyToOne
    private Usuario comprador;
    @NotNull @Enumerated(EnumType.STRING)
    private GatewayPagamento gatewayPagamento;
    @NotNull @Enumerated(EnumType.STRING)
    private Status status;

    public Compra(Integer quantidade, Produto produto, Usuario comprador, GatewayPagamento gatewayPagamento, Status status) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.comprador = comprador;
        this.gatewayPagamento = gatewayPagamento;
        this.status = status;
    }
    @Deprecated
    public Compra(){}

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "quantidade=" + quantidade +
                ", gatewayPagamento=" + gatewayPagamento +
                ", status=" + status +
                '}';
    }
}
