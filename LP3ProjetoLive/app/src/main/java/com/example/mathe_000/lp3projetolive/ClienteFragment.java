package com.example.mathe_000.lp3projetolive;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mathe_000.lp3projetolive.databinding.FragmentClienteBinding;
import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;





public class ClienteFragment extends Fragment {
    private static final String KEY_PRODUCT_ID = "Cliente_id";

    private FragmentClienteBinding mBinding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate this data binding layout
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cliente, container, false);

        // Create and set the adapter for the RecyclerView.
        return mBinding.getRoot();
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ClienteViewModel.Factory factory = new ClienteViewModel.Factory(
                getActivity().getApplication(), getArguments().getInt(KEY_PRODUCT_ID));

        final ClienteViewModel model = ViewModelProviders.of(this, factory)
                .get(ClienteViewModel.class);

        mBinding.setClienteViewModel(model);

        subscribeToModel(model);
    }

    private void subscribeToModel(final ClienteViewModel model) {


        model.getObservableCliente().observe(this, new Observer<Cliente>() {
            @Override
            public void onChanged(@Nullable Cliente cliente) {
                model.setCliente(cliente);
            }
        });


    }

    /** Creates product fragment for specific product ID */
    public static ClienteFragment forClient(int clienteId) {
        ClienteFragment fragment = new ClienteFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_PRODUCT_ID, clienteId);
        fragment.setArguments(args);
        return fragment;
    }
}
