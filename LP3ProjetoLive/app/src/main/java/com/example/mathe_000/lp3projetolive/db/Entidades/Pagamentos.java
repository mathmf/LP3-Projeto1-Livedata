package com.example.mathe_000.lp3projetolive.db.Entidades;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

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
        indices = {@Index(value = "clienteId")
        })
public class Pagamentos {

    @PrimaryKey(autoGenerate = true)
    private int Id;
    private int clienteId;
    private int produtoId;
    private String Local;

    public Pagamentos() {
    }

    public Pagamentos(int clienteId, int produtoId, String local) {
        this.clienteId = clienteId;
        this.produtoId = produtoId;
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
}
