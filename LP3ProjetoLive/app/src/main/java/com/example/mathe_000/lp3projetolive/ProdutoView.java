package com.example.mathe_000.lp3projetolive;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ProdutoView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bundle info = intent.getExtras();
        Produto viewPro = new Produto();
        if(info!=null) viewPro = (Produto) info.getSerializable("bundle");

        TextView t1 = findViewById(R.id.NomePro);
        TextView t2 = findViewById(R.id.PrecoPro);
        TextView t3 = findViewById(R.id.DescPro);

        t1.setText(viewPro.getNome());
        t2.setText(Double.toString(viewPro.getPreco()));
        t3.setText(viewPro.getDesc());


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
