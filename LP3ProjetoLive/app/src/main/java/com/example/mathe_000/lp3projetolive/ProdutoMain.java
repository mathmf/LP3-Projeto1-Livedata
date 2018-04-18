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

import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;
import com.example.mathe_000.lp3projetolive.db.Entidades.Produto;

import java.util.ArrayList;

public class ProdutoMain extends AppCompatActivity {

    Bundle newActivityInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (savedInstanceState == null) {
            ProdutoListFragment fragment = new ProdutoListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container_pro, fragment, ClienteListFragment.TAG).commit();
        }


    }

    public void show(Produto produto) {

        ProdutoFragment produtoFragment = ProdutoFragment.forProduto(produto.getId());

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("product")
                .replace(R.id.fragment_container_pro,
                        produtoFragment, null).commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_edit);
        newActivityInfo = new Bundle();
        newActivityInfo.putSerializable("id",produto);
    }



    public void proBtn(View clickedBtn){
        Intent activityIntent =
                new Intent(this, ProdutoEdit.class);
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
