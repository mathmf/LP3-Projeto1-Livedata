package com.example.mathe_000.lp3projetolive;

/**
 * Created by mathe_000 on 14/04/2018.
 */

public class Pagamentos {

    private Cliente cliente;
    private Produto produto;
    private int parcelas;

    public Pagamentos(Cliente cliente, Produto produto, int parcelas) {
        this.cliente = cliente;
        this.produto = produto;
        this.parcelas = parcelas;
    }

    public Pagamentos() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }
}
