package com.example.mathe_000.lp3projetolive.db.Entidades;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by mathe_000 on 14/04/2018.
 */

@Entity(tableName = "Pagamentos",
        foreignKeys = {
                @ForeignKey(entity = Cliente.class,
                        parentColumns = "id",
                        childColumns = "clienteId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Produto.class,
                        parentColumns = "id",
                        childColumns = "produtoId",
                        onDelete = ForeignKey.CASCADE)},
        indices = {@Index(value = "clienteId"),
        })
public class Pagamentos implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int Id;
    private int clienteId;
    private int produtoId;
    private String cliente;
    private String produto;
    private String Local;

    public Pagamentos() {
    }

    public Pagamentos(int clienteId, int produtoId, String cliente, String produto, String local) {
        this.clienteId = clienteId;
        this.produtoId = produtoId;
        this.cliente = cliente;
        this.produto = produto;
        Local = local;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public String getLocal() {
        return Local;
    }

    public void setLocal(String local) {
        Local = local;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }
}
