package com.example.mathe_000.lp3projetolive;

import java.io.Serializable;

/**
 * Created by mathe_000 on 14/04/2018.
 */

public class Produto implements Serializable {

    private String Nome;
    private double Preco;
    private String Desc;

    public Produto(String nome, double preco, String desc) {
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

    public double getPreco() {
        return Preco;
    }

    public void setPreco(double preco) {
        Preco = preco;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }
}
