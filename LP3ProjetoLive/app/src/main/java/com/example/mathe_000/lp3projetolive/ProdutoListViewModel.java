package com.example.mathe_000.lp3projetolive;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.os.AsyncTask;

import com.example.mathe_000.lp3projetolive.db.ClienteDatabase;
import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;
import com.example.mathe_000.lp3projetolive.db.Entidades.Produto;

import java.util.List;

public class ProdutoListViewModel extends AndroidViewModel {
    private final MediatorLiveData<List<Produto>> mObservableProdutos;
    private final ClienteRepositorio mClienteRep;

    public ProdutoListViewModel(Application application) {
        super(application);

        mObservableProdutos = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservableProdutos.setValue(null);
        AppExecutors mAppExecutors = new AppExecutors();
        mClienteRep = ClienteRepositorio.getInstance(ClienteDatabase.getDatabaseInstance(application, mAppExecutors));
        LiveData<List<Produto>> produtos = mClienteRep.getProdutos();

        // observe the changes of the products from the database and forward them
        mObservableProdutos.addSource(produtos, mObservableProdutos::setValue);
    }

    /**
     * Expose the LiveData Products query so the UI can observe it.
     */
    public LiveData<List<Produto>> getProdutos() {
        return mObservableProdutos;
    }


}
