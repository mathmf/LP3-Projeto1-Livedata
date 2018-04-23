package com.example.mathe_000.lp3projetolive;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;
import com.example.mathe_000.lp3projetolive.db.Entidades.Pagamentos;

public class PagamentoMain extends AppCompatActivity {

    Bundle newActivityInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            PagamentoListFragment fragment = new PagamentoListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_pag, fragment, PagamentoListFragment.TAG).commit();
        }
    }

    public void show(Pagamentos pagamentos) {

        PagamentoFragment pagamentoFragment = PagamentoFragment.forPagamento(pagamentos.getId());

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("product")
                .replace(R.id.fragment_container_pag,
                        pagamentoFragment, null).commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_edit);
        newActivityInfo = new Bundle();
        newActivityInfo.putSerializable("id",pagamentos);
    }

    public void pagBtn(View clickedBtn){
        Intent activityIntent =
                new Intent(this, ClienteEdit.class);
        if(newActivityInfo!=null){
            activityIntent.putExtras(newActivityInfo);
        }
        startActivity(activityIntent);
    }

    @Override
    public void onBackPressed (){
        super.onBackPressed();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_circle);
        newActivityInfo = null;
    }



}
