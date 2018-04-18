package com.example.mathe_000.lp3projetolive;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.mathe_000.lp3projetolive.db.ClienteDatabase;
import com.example.mathe_000.lp3projetolive.db.Entidades.Produto;

public class ProdutoViewModel extends AndroidViewModel {

    private final LiveData<Produto> mObservableProduto;

    public ObservableField<Produto> produto = new ObservableField<>();

    private final int mProdutoId;

    public ProdutoViewModel(@NonNull Application application, ClienteRepositorio repository,
                            final int produtoId) {
        super(application);
        mProdutoId = produtoId;

        mObservableProduto = repository.loadProduto(mProdutoId);
    }


    public LiveData<Produto> getObservableProduto() {
        return mObservableProduto;
    }

    public void setProduto(Produto produto) {
        this.produto.set(produto);
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
            return (T) new ProdutoViewModel(mApplication, mRepository, mProductId);
        }
    }
}
