package com.example.mathe_000.lp3projetolive;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mathe_000.lp3projetolive.databinding.FragmentPagamentoListBinding;
import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;
import com.example.mathe_000.lp3projetolive.db.Entidades.Pagamentos;

import java.util.List;

public class PagamentoListFragment extends Fragment {


    public static final String TAG = "PagamentoListViewModel";

    private PagamentoAdapter mPagamentoAdapter;

    private FragmentPagamentoListBinding mBinding;

    private PagamentoListViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pagamento_list, container, false);

        mPagamentoAdapter = new PagamentoAdapter(mPagamentoClickCallback);
        mBinding.pagamentosList.setAdapter(mPagamentoAdapter);





        return mBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(PagamentoListViewModel.class);

        subscribeUi(viewModel);
    }

    public void subscribeUi(PagamentoListViewModel viewModel) {


        viewModel.getPagamentos().observe(this, new Observer<List<Pagamentos>>() {
            @Override
            public void onChanged(@Nullable List<Pagamentos> myPagamentos) {
                if (myPagamentos != null) {
                    mBinding.setIsLoading(false);
                    mPagamentoAdapter.setmPagamentosList(myPagamentos);
                } else {
                    mBinding.setIsLoading(true);
                }

                mBinding.executePendingBindings();
            }
        });
    }

    private final PagamentoClickCallback mPagamentoClickCallback = new PagamentoClickCallback() {
        @Override
        public void onClick(Pagamentos pagamentos) {


            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((PagamentoMain) getActivity()).show(pagamentos);
            }
        }
    };
}
