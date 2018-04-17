package com.example.mathe_000.lp3projetolive;

import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    public static List<Cliente> generateProducts() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("testa",111111111,001));
        clientes.add(new Cliente("teste",222222222,002));
        clientes.add(new Cliente("testi",333333333,003));
        clientes.add(new Cliente("testo",444445444,004));
        clientes.add(new Cliente("testu",555555555,005));

        return clientes;
    }

}
