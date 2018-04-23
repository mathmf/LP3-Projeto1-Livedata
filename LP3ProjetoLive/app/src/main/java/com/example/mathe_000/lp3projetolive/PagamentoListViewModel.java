package com.example.mathe_000.lp3projetolive;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.example.mathe_000.lp3projetolive.db.ClienteDatabase;
import com.example.mathe_000.lp3projetolive.db.Entidades.Pagamentos;

import java.util.List;

public class PagamentoListViewModel extends AndroidViewModel {
    private final MediatorLiveData<List<Pagamentos>> mObservablePagamentos;
    private final ClienteRepositorio mClienteRep;

    public PagamentoListViewModel(Application application) {
        super(application);

        mObservablePagamentos = new MediatorLiveData<>();

        mObservablePagamentos.setValue(null);
        AppExecutors mAppExecutors = new AppExecutors();
        mClienteRep = ClienteRepositorio.getInstance(ClienteDatabase.getDatabaseInstance(application, mAppExecutors));
        LiveData<List<Pagamentos>> pagamentos = mClienteRep.getPagamentos();


        mObservablePagamentos.addSource(pagamentos, mObservablePagamentos::setValue);
    }


    public LiveData<List<Pagamentos>> getPagamentos() {
        return mObservablePagamentos;
    }


}
