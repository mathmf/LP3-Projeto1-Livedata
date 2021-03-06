package com.example.mathe_000.lp3projetolive;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.example.mathe_000.lp3projetolive.db.ClienteDatabase;
import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;
import com.example.mathe_000.lp3projetolive.db.Entidades.Pagamentos;
import com.example.mathe_000.lp3projetolive.db.Entidades.Produto;

import java.util.List;

public class ClienteRepositorio {

    private static ClienteRepositorio sInstance;

    private final ClienteDatabase mDatabase;
    private MediatorLiveData<List<Cliente>> mObservableClientes;
    private MediatorLiveData<List<Produto>> mObservableProdutos;
    private MediatorLiveData<List<Pagamentos>> mObservablePagamentos;

    private ClienteRepositorio(final ClienteDatabase database) {
        mDatabase = database;
        mObservableClientes = new MediatorLiveData<>();

        mObservableClientes.addSource(mDatabase.clienteDao().getAllClientes(),
                clientesEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservableClientes.postValue(clientesEntities);
                    }
                });
        mObservableProdutos = new MediatorLiveData<>();
        mObservableProdutos.addSource(mDatabase.produtoDao().getAllProdutos(),
                produtosEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservableProdutos.postValue(produtosEntities);
                    }
                });
        mObservablePagamentos = new MediatorLiveData<>();
        mObservablePagamentos.addSource(mDatabase.pagamentosDao().getAllPagamentos(),
                pagamentosEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservablePagamentos.postValue(pagamentosEntities);
                    }
                });
    }

    public static ClienteRepositorio getInstance(final ClienteDatabase database) {
        if (sInstance == null) {
            synchronized (ClienteRepositorio.class) {
                if (sInstance == null) {
                    sInstance = new ClienteRepositorio(database);
                }
            }
        }
        return sInstance;
    }


    public LiveData<List<Cliente>> getClientes() {
        return mObservableClientes;
    }

    public LiveData<Cliente> loadCliente(final int clienteId) {
        return mDatabase.clienteDao().getClienteById(clienteId);
    }


    public LiveData<List<Produto>> getProdutos() {
        return mObservableProdutos;
    }

    public LiveData<Produto> loadProduto(final int produtoId) {
        return mDatabase.produtoDao().getProdutoById(produtoId);
    }

    public LiveData<List<Pagamentos>> getPagamentos() {
        return mObservablePagamentos;
    }

    public LiveData<Pagamentos> loadPagamento(final int pagamentoId) {
        return mDatabase.pagamentosDao().getPagamentobyId(pagamentoId);
    }


}
