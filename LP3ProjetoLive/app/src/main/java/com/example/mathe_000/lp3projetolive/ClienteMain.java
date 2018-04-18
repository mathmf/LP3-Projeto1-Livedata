package com.example.mathe_000.lp3projetolive;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mathe_000.lp3projetolive.db.ClienteDatabase;
import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteMain extends AppCompatActivity {

    Bundle newActivityInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_edit);
        newActivityInfo = new Bundle();
        newActivityInfo.putSerializable("id",cliente);
    }



    public void cliBtn(View clickedBtn){
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
