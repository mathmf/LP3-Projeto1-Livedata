package com.example.mathe_000.lp3projetolive;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mathe_000.lp3projetolive.db.ClienteDatabase;
import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;
import com.example.mathe_000.lp3projetolive.db.Entidades.Produto;

import java.util.ArrayList;
import java.util.List;

public class ClienteEdit extends AppCompatActivity {

    Cliente editCli;
    TextView t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_edit);

        Intent intent = getIntent();
        Bundle info = intent.getExtras();
        editCli = new Cliente();
        t1 = findViewById(R.id.NomeCli);
        t2 = findViewById(R.id.CPFCli);
        Button b = findViewById(R.id.delCliBtn);
        if(info!=null) {
            editCli = (Cliente) info.getSerializable("id");
            t1.setText(editCli.getNome());
            t2.setText(Integer.toString(editCli.getCPF()));
            getSupportActionBar().setTitle("Atualizar Cliente");
            b.setVisibility(View.VISIBLE);
        }
        else{
            t1.setText("");
            t2.setText("");
            getSupportActionBar().setTitle("Criar Cliente");
            b.setVisibility(View.GONE);
        }

    }

    public void btnCliente(View btn){
        final StringBuilder sb = new StringBuilder(t1.getText().length());
        sb.append(t1.getText());
        editCli.setNome(sb.toString());
        final StringBuilder sb2 = new StringBuilder(t2.getText().length());
        sb2.append(t2.getText());
        editCli.setCPF(Integer.parseInt(sb2.toString()));
        AppExecutors appExecutors = new AppExecutors();
        ClienteDatabase db = ClienteDatabase.getDatabaseInstance(this,appExecutors);
        List<Cliente> List = new ArrayList<>();
        List.add(editCli);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.clienteDao().insertAll(List);
            }
        });
        finish();
    }

    public void delCliBtn(View btn){
        AppExecutors appExecutors = new AppExecutors();
        ClienteDatabase db = ClienteDatabase.getDatabaseInstance(this,appExecutors);
        Cliente c = editCli;
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.clienteDao().delete(c);
            }
        });
        finish();
    }



}
