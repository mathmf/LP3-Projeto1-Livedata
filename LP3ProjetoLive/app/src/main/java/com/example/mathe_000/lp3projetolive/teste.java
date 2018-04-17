package com.example.mathe_000.lp3projetolive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;

public class teste extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);
        if (savedInstanceState == null) {
            ClienteListFragment fragment = new ClienteListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, ClienteListFragment.TAG).commit();
        }


    }

    public void show(Cliente cliente) {

        ClienteFragment clienteFragment = ClienteFragment.forClient(cliente.getId());

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("product")
                .replace(R.id.fragment_container,
                        clienteFragment, null).commit();
    }

}
