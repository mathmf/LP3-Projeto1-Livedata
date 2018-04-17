package com.example.mathe_000.lp3projetolive;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;

public class ClienteView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_view);
        Intent intent = getIntent();
        Bundle info = intent.getExtras();
        Cliente viewCli = new Cliente();
        if(info!=null) viewCli = (Cliente) info.getSerializable("bundle");

        TextView t1 = findViewById(R.id.NomeCli);
        TextView t2 = findViewById(R.id.CPFCli);

        t1.setText(viewCli.getNome());
        t2.setText(Integer.toString(viewCli.getCPF()));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
