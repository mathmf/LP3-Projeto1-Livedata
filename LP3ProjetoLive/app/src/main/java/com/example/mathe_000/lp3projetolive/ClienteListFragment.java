package com.example.mathe_000.lp3projetolive;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;
import com.example.mathe_000.lp3projetolive.databinding.FragmentClienteListBinding;


import java.util.List;


public class ClienteListFragment extends Fragment {

    public static final String TAG = "ClienteListViewModel";

    private ClienteAdapter mClienteAdapter;

    private FragmentClienteListBinding mBinding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cliente_list, container, false);

        mClienteAdapter = new ClienteAdapter(mClienteClickCallback);
        mBinding.clientesList.setAdapter(mClienteAdapter);

        return mBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ClienteListViewModel viewModel = ViewModelProviders.of(this).get(ClienteListViewModel.class);

        subscribeUi(viewModel);
    }

    public void subscribeUi(ClienteListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getClientes().observe(this, new Observer<List<Cliente>>() {
            @Override
            public void onChanged(@Nullable List<Cliente> myClientes) {
                if (myClientes != null) {
                    mBinding.setIsLoading(false);
                    mClienteAdapter.setClienteList(myClientes);
                } else {
                    mBinding.setIsLoading(true);
                }
                // espresso does not know how to wait for data binding's loop so we execute changes
                // sync.
                mBinding.executePendingBindings();
            }
        });
    }

    private final ClienteClickCallback mClienteClickCallback = new ClienteClickCallback() {
        @Override
        public void onClick(Cliente cliente) {

            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((ClienteMain) getActivity()).show(cliente);
            }
        }
    };
}
