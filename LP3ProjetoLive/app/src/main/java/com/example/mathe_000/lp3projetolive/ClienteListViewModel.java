package com.example.mathe_000.lp3projetolive;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.example.mathe_000.lp3projetolive.db.ClienteDatabase;
import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;
import com.example.mathe_000.lp3projetolive.BasicApp;

import java.util.List;

public class ClienteListViewModel extends AndroidViewModel {
    private final MediatorLiveData<List<Cliente>> mObservableClientes;

    public ClienteListViewModel(Application application) {
        super(application);

        mObservableClientes = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservableClientes.setValue(null);
        AppExecutors mAppExecutors = new AppExecutors();
        LiveData<List<Cliente>> clientes = ClienteRepositorio.getInstance(ClienteDatabase.getDatabaseInstance(application, mAppExecutors))
                .getClientes();

        // observe the changes of the products from the database and forward them
        mObservableClientes.addSource(clientes, mObservableClientes::setValue);
    }

    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    public LiveData<List<Cliente>> getClientes() {
        return mObservableClientes;
    }

}
