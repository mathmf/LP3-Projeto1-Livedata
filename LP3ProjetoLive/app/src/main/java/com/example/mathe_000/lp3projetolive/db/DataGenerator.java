package com.example.mathe_000.lp3projetolive.db;

import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;
import com.example.mathe_000.lp3projetolive.db.Entidades.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    public static List<Cliente> generateClientes() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("testa",111111111,001));
        clientes.add(new Cliente("teste",222222222,002));
        clientes.add(new Cliente("testi",333333333,003));
        clientes.add(new Cliente("testo",444444444,004));
        clientes.add(new Cliente("testu",555555555,005));

        return clientes;
    }

    public static List<Produto> generateProdutos(){
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Carro",51420,"Um carro semi novo"));
        produtos.add(new Produto("Casa",514200,"Uma casa nova"));
        produtos.add(new Produto("Gato",5142,"Um filhote de gato"));

        return produtos;
    }

}
