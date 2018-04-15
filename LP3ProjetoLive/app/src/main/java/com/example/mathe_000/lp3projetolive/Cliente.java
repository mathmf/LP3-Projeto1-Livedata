package com.example.mathe_000.lp3projetolive;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
/**
 * Created by mathe_000 on 13/04/2018.
 */

@Entity(tableName = "Clientes")
public class Cliente implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
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
