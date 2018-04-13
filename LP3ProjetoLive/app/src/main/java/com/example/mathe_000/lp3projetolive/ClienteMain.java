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

public class ClienteMain extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_main);
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

        listView = (ListView) findViewById(R.id.ListaCli);

        //implementar com BD e LiveData
        Cliente c1 = new Cliente("teste",11111111);
        Cliente c2 = new Cliente("testa",22222222);
        Cliente c3 = new Cliente("testo",33333333);

        final ArrayList<Cliente> ClienteLista = new ArrayList<>();

        ClienteLista.add(c1);
        ClienteLista.add(c2);
        ClienteLista.add(c3);

        ArrayList<String> values = new ArrayList<>();

        for(int i = 0;i<ClienteLista.size();i++){
            values.add(ClienteLista.get(i).getNome());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent activityIntent = new Intent(view.getContext(),ClienteView.class);
                Bundle newActivityInfo = new Bundle();
                newActivityInfo.putSerializable("bundle",ClienteLista.get(position));
                activityIntent.putExtras(newActivityInfo);
                startActivity(activityIntent);
            }
        });



    }

}
