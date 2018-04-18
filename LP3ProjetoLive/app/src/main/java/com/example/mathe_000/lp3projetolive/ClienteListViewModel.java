package com.example.mathe_000.lp3projetolive;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.example.mathe_000.lp3projetolive.db.ClienteDatabase;
import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;
import com.example.mathe_000.lp3projetolive.BasicApp;

import java.util.List;

public class ClienteListViewModel extends AndroidViewModel {
    private final MediatorLiveData<List<Cliente>> mObservableClientes;
    private final ClienteRepositorio mClienteRep;

    public ClienteListViewModel(Application application) {
        super(application);

        mObservableClientes = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservableClientes.setValue(null);
        AppExecutors mAppExecutors = new AppExecutors();
        mClienteRep = ClienteRepositorio.getInstance(ClienteDatabase.getDatabaseInstance(application, mAppExecutors));
        LiveData<List<Cliente>> clientes = mClienteRep.getClientes();

        // observe the changes of the products from the database and forward them
        mObservableClientes.addSource(clientes, mObservableClientes::setValue);
    }

    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    public LiveData<List<Cliente>> getClientes() {
        return mObservableClientes;
    }

    public void delCliente(Cliente id){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                mClienteRep.delCliente(id);
            }
        });



    }

}
