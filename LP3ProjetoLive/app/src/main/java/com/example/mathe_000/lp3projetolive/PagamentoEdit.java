package com.example.mathe_000.lp3projetolive;

import android.Manifest;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mathe_000.lp3projetolive.db.ClienteDatabase;
import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;
import com.example.mathe_000.lp3projetolive.db.Entidades.Pagamentos;
import com.example.mathe_000.lp3projetolive.db.Entidades.Produto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PagamentoEdit extends AppCompatActivity {

    private ClienteRepositorio mClienteRep;
    public Spinner sp1, sp2;
    public ArrayList<String> Cliente, Produto;
    public ArrayList<Integer> ClienteId,ProdutoId;
    public Pagamentos pagamentoEdit;
    public TextView loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento_edit);
        Intent intent = getIntent();
        Bundle info = intent.getExtras();
        pagamentoEdit = new Pagamentos();
        Button b = findViewById(R.id.delPagBtn);
        loc = findViewById(R.id.LocalText);
        if(info!=null) {
            pagamentoEdit = (Pagamentos) info.getSerializable("id");
            getSupportActionBar().setTitle("Atualizar Pagamento");
            loc.setText(pagamentoEdit.getLocal());
            b.setVisibility(View.VISIBLE);
        }
        else{
            loc.setText("Não Informado");
            getSupportActionBar().setTitle("Criar Pagamento");
            b.setVisibility(View.GONE);
        }
        Cliente = new ArrayList<>();
        ClienteId = new ArrayList<>();
        ArrayAdapter<String> CliAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item);
        ArrayAdapter<String> ProAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item);
        sp1 = findViewById(R.id.CliSpi);
        sp2 = findViewById(R.id.ProSpi);
        Produto = new ArrayList<>();
        ProdutoId = new ArrayList<>();
        AppExecutors mAppExecutors = new AppExecutors();
        mClienteRep = ClienteRepositorio.getInstance(ClienteDatabase.getDatabaseInstance(this, mAppExecutors));
        LiveData<List<Cliente>> clientes = mClienteRep.getClientes();
        clientes.observe(this,new Observer<List<Cliente>>() {
            @Override
            public void onChanged(@Nullable List<Cliente> myClientes) {
                if (myClientes != null) {
                    Cliente.clear();
                    ClienteId.clear();
                    for(int i = 0;i<myClientes.size();i++){
                        Cliente.add(myClientes.get(i).getNome());
                        ClienteId.add(myClientes.get(i).getId());
                    }
                    CliAdapter.addAll(Cliente);
                    sp1.setAdapter(CliAdapter);
                }
            }
        });
        LiveData<List<Produto>> produtos = mClienteRep.getProdutos();
        produtos.observe(this,new Observer<List<Produto>>() {
            @Override
            public void onChanged(@Nullable List<Produto> myProdutos) {
                if (myProdutos != null) {
                    Produto.clear();
                    ProdutoId.clear();
                    for(int i = 0;i<myProdutos.size();i++){
                        Produto.add(myProdutos.get(i).getNome());
                        ProdutoId.add(myProdutos.get(i).getId());
                    }
                    ProAdapter.addAll(Produto);
                    sp2.setAdapter(ProAdapter);
                }
            }
        });


    }

    public void btnPagamento(View view){
        int spCli = sp1.getSelectedItemPosition();
        int spPro = sp2.getSelectedItemPosition();
        pagamentoEdit.setCliente(Cliente.get(spCli));
        pagamentoEdit.setClienteId(ClienteId.get(spCli));
        pagamentoEdit.setProduto(Produto.get(spPro));
        pagamentoEdit.setProdutoId(ProdutoId.get(spPro));
        final StringBuilder sb = new StringBuilder(loc.getText().length());
        sb.append(loc.getText());
        pagamentoEdit.setLocal(sb.toString());
        AppExecutors appExecutors = new AppExecutors();
        ClienteDatabase db = ClienteDatabase.getDatabaseInstance(this,appExecutors);
        List<Pagamentos> List = new ArrayList<>();
        List.add(pagamentoEdit);
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.pagamentosDao().insertAll(List);
            }
        });
        finish();
    }

    public void delPagBtn(View view){
        AppExecutors appExecutors = new AppExecutors();
        ClienteDatabase db = ClienteDatabase.getDatabaseInstance(this,appExecutors);
        Pagamentos c = pagamentoEdit;
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.pagamentosDao().delete(c);
            }
        });
        finish();

    }

    public void gpsBtn(View view){
        Log.d("teste","GPS");
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        String result = null;
        int i = 0;
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},i );
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        try {
            List<Address> list = geocoder.getFromLocation(
                    location.getLatitude(), location.getLongitude(), 1);
            if (list != null && list.size() > 0) {
                Address address = list.get(0);
                // sending back first address line and locality
                result = address.getAddressLine(0) + ", " + address.getLocality();
            }
        } catch (IOException e) {
            Log.e("error", "Impossible to connect to Geocoder", e);
        }
        loc.setText(result);

    }
}
