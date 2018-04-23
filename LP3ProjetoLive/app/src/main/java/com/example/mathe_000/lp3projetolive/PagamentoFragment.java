package com.example.mathe_000.lp3projetolive;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.mathe_000.lp3projetolive.databinding.FragmentPagamentoBinding;
import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;
import com.example.mathe_000.lp3projetolive.db.Entidades.Pagamentos;

public class PagamentoFragment extends Fragment {

    private static final String KEY_PRODUCT_ID = "Pagamento_id";

    private FragmentPagamentoBinding mBinding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate this data binding layout
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pagamento, container, false);

        // Create and set the adapter for the RecyclerView.
        return mBinding.getRoot();
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        PagamentoViewModel.Factory factory = new PagamentoViewModel.Factory(
                getActivity().getApplication(), getArguments().getInt(KEY_PRODUCT_ID));

        final PagamentoViewModel model = ViewModelProviders.of(this, factory)
                .get(PagamentoViewModel.class);

        mBinding.setPagamentoViewModel(model);

        subscribeToModel(model);
    }

    private void subscribeToModel(final PagamentoViewModel model) {


        model.getObservablePagamento().observe(this, new Observer<Pagamentos>() {
            @Override
            public void onChanged(@Nullable Pagamentos pagamentos) {
                model.setPagamento(pagamentos);
            }
        });


    }

    /** Creates Cliente fragment for specific product ID */
    public static PagamentoFragment forPagamento(int pagamentoId) {
        PagamentoFragment fragment = new PagamentoFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_PRODUCT_ID, pagamentoId);
        fragment.setArguments(args);
        return fragment;
    }
}
