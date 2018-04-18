package com.example.mathe_000.lp3projetolive.db.Entidades;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by mathe_000 on 14/04/2018.
 */
@Entity(tableName = "Produtos")
public class Produto implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String Nome;
    private int Preco;
    private String Desc;

    public Produto(String nome, int preco, String desc) {
        Nome = nome;
        Preco = preco;
        Desc = desc;
    }

    public Produto() {
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public int getPreco() {
        return Preco;
    }

    public void setPreco(int preco) {
        Preco = preco;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
