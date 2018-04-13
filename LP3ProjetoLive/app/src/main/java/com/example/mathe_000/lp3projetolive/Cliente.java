package com.example.mathe_000.lp3projetolive;

import java.io.Serializable;

/**
 * Created by mathe_000 on 13/04/2018.
 */

public class Cliente implements Serializable {

    private String Nome;
    private int CPF;

    public Cliente(){

    }

    public Cliente(String nome, int CPF) {
        Nome = nome;
        this.CPF = CPF;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public int getCPF() {
        return CPF;
    }

    public void setCPF(int CPF) {
        this.CPF = CPF;
    }
}
