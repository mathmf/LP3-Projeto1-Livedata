package com.example.mathe_000.lp3projetolive;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;

import java.util.List;

/**
 * Created by mathe_000 on 15/04/2018.
 */

public class ClienteViewModel extends AndroidViewModel {

    private LiveData<List<Cliente>> mClientes;
    private ClienteDao mClienteDao;

    public ClienteViewModel(Application application) {
        super(application);

        mClienteDao = ClienteDatabase.getDatabaseInstance(application).clienteDao();
        mClientes = mClienteDao.getAllClientes();
    }

    // Use LiveData for getting all the data from the database
    public LiveData<List<Cliente>> getTasks() {
        return mClientes;
    }
}
