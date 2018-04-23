package com.example.mathe_000.lp3projetolive;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.example.mathe_000.lp3projetolive.db.ClienteDatabase;
import com.example.mathe_000.lp3projetolive.db.Entidades.Pagamentos;

public class PagamentoViewModel extends AndroidViewModel {
    private final LiveData<Pagamentos> mObservablePagamento;

    public ObservableField<Pagamentos> pagamento = new ObservableField<>();

    private final int mPagamentoId;

    public PagamentoViewModel(@NonNull Application application, ClienteRepositorio repository,
                            final int pagamentoId) {
        super(application);
        mPagamentoId = pagamentoId;

        mObservablePagamento = repository.loadPagamento(mPagamentoId);
    }


    public LiveData<Pagamentos> getObservablePagamento() {
        return mObservablePagamento;
    }

    public void setPagamento(Pagamentos pagamento) {
        this.pagamento.set(pagamento);
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
            return (T) new PagamentoViewModel(mApplication, mRepository, mProductId);
        }
    }

}
