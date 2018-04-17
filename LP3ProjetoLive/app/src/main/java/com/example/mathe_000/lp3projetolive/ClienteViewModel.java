package com.example.mathe_000.lp3projetolive;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.mathe_000.lp3projetolive.db.Dao.ClienteDao;
import com.example.mathe_000.lp3projetolive.db.ClienteDatabase;
import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;

import java.util.List;

/**
 * Created by mathe_000 on 15/04/2018.
 */

public class ClienteViewModel extends AndroidViewModel {


    private final LiveData<Cliente> mObservableCliente;

    public ObservableField<Cliente> cliente = new ObservableField<>();

    private final int mClienteId;

    public ClienteViewModel(@NonNull Application application, ClienteRepositorio repository,
                            final int clienteId) {
        super(application);
        mClienteId = clienteId;

        mObservableCliente = repository.loadCliente(mClienteId);
    }


    public LiveData<Cliente> getObservableCliente() {
        return mObservableCliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente.set(cliente);
    }


    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final int mProductId;

        private final ClienteRepositorio mRepository;

        public Factory(@NonNull Application application, int productId) {
            mApplication = application;
            mProductId = productId;
            AppExecutors mAppExecutors = new AppExecutors();
            mRepository =  ClienteRepositorio.getInstance(ClienteDatabase.getDatabaseInstance(application, mAppExecutors));
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new ClienteViewModel(mApplication, mRepository, mProductId);
        }
    }

}
