package com.example.mathe_000.lp3projetolive.db.Entidades;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
/**
 * Created by mathe_000 on 13/04/2018.
 */

@Entity(tableName = "Clientes")
public class Cliente implements Serializable {

    @PrimaryKey
    private int id;
    private String Nome;
    private int CPF;

    public Cliente(){

    }

    public Cliente(String nome, int CPF, int id) {
        Nome = nome;
        this.CPF = CPF;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
