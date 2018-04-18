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

import com.example.mathe_000.lp3projetolive.databinding.FragmentClienteListBinding;
import com.example.mathe_000.lp3projetolive.databinding.FragmentProdutoListBinding;
import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;
import com.example.mathe_000.lp3projetolive.db.Entidades.Produto;

import java.util.List;

public class ProdutoListFragment extends Fragment {

    public static final String TAG = "ProdutoListViewModel";

    private ProdutoAdapter mProdutoAdapter;

    private FragmentProdutoListBinding mBinding;

    private ProdutoListViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_produto_list, container, false);

        mProdutoAdapter = new ProdutoAdapter(mProdutoClickCallback);
        mBinding.produtosList.setAdapter(mProdutoAdapter);

        return mBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ProdutoListViewModel.class);

        subscribeUi(viewModel);
    }

    public void subscribeUi(ProdutoListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getProdutos().observe(this, new Observer<List<Produto>>() {
            @Override
            public void onChanged(@Nullable List<Produto> myProdutos) {
                if (myProdutos != null) {
                    mBinding.setIsLoading(false);
                    mProdutoAdapter.setProdutoList(myProdutos);
                } else {
                    mBinding.setIsLoading(true);
                }
                // espresso does not know how to wait for data binding's loop so we execute changes
                // sync.
                mBinding.executePendingBindings();
            }
        });
    }

    private final ProdutoClickCallback mProdutoClickCallback = new ProdutoClickCallback() {
        @Override
        public void onClick(Produto produto) {


            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((ProdutoMain) getActivity()).show(produto);
            }
        }
    };

}
