package com.example.mathe_000.lp3projetolive;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mathe_000.lp3projetolive.db.ClienteDatabase;
import com.example.mathe_000.lp3projetolive.db.Entidades.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoEdit extends AppCompatActivity {

    Produto editPro;
    TextView t1,t2,t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_edit);

        Intent intent = getIntent();
        Bundle info = intent.getExtras();
        editPro = new Produto();
        t1 = findViewById(R.id.NomePro);
        t2 = findViewById(R.id.PrecoPro);
        t3 = findViewById(R.id.DescPro);
        Button b = findViewById(R.id.delProBtn);
        if(info!=null) {
            editPro = (Produto) info.getSerializable("id");
            t1.setText(editPro.getNome());
            t2.setText(Integer.toString(editPro.getPreco()));
            t3.setText(editPro.getDesc());
            getSupportActionBar().setTitle("Atualizar Produto");
            b.setVisibility(View.VISIBLE);
        }
        else{
            t1.setText("");
            t2.setText("");
            t3.setText("");
            getSupportActionBar().setTitle("Criar Produto");
            b.setVisibility(View.GONE);
        }

    }

    public void btnProduto(View btn){
        final StringBuilder sb = new StringBuilder(t1.getText().length());
        sb.append(t1.getText());
        editPro.setNome(sb.toString());
        final StringBuilder sb2 = new StringBuilder(t2.getText().length());
        sb2.append(t2.getText());
        editPro.setPreco(Integer.parseInt(sb2.toString()));
        final StringBuilder sb3 = new StringBuilder(t3.getText().length());
        sb3.append(t3.getText());
        editPro.setDesc(sb3.toString());
        AppExecutors appExecutors = new AppExecutors();
        ClienteDatabase db = ClienteDatabase.getDatabaseInstance(this,appExecutors);
        List<Produto> List = new ArrayList<>();
        List.add(editPro);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.produtoDao().insertAll(List);
            }
        });
        finish();
    }

    public void delProBtn(View btn){
        AppExecutors appExecutors = new AppExecutors();
        ClienteDatabase db = ClienteDatabase.getDatabaseInstance(this,appExecutors);
        Produto c = editPro;
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.produtoDao().delete(c);
            }
        });
        finish();
    }
}
