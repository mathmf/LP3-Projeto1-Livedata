package com.example.mathe_000.lp3projetolive;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ProdutoMain extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_main);
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

        listView = (ListView) findViewById(R.id.ListaPro);

        //implementar com BD e LiveData
        Produto p1 = new Produto("Carro",5142.00,"Um carro semi novo");
        Produto p2 = new Produto("Casa",51420.00,"Uma casa nova");
        Produto p3 = new Produto("Gato",51.42,"Um filhote de gato");


        final ArrayList<Produto> ProdutoLista = new ArrayList<>();

        ProdutoLista.add(p1);
        ProdutoLista.add(p2);
        ProdutoLista.add(p3);

        ArrayList<String> values = new ArrayList<>();

        for(int i = 0;i<ProdutoLista.size();i++){
            values.add(ProdutoLista.get(i).getNome());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent activityIntent = new Intent(view.getContext(),ProdutoView.class);
                Bundle newActivityInfo = new Bundle();
                newActivityInfo.putSerializable("bundle",ProdutoLista.get(position));
                activityIntent.putExtras(newActivityInfo);
                startActivity(activityIntent);
            }
        });



    }

}
