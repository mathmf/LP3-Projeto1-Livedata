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

import com.example.mathe_000.lp3projetolive.databinding.FragmentProdutoBinding;
import com.example.mathe_000.lp3projetolive.db.Entidades.Cliente;
import com.example.mathe_000.lp3projetolive.db.Entidades.Produto;

public class ProdutoFragment extends Fragment {

    private static final String KEY_PRODUCT_ID = "Produto_id";

    private FragmentProdutoBinding mBinding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate this data binding layout
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_produto, container, false);

        // Create and set the adapter for the RecyclerView.
        return mBinding.getRoot();
    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ProdutoViewModel.Factory factory = new ProdutoViewModel.Factory(
                getActivity().getApplication(), getArguments().getInt(KEY_PRODUCT_ID));

        final ProdutoViewModel model = ViewModelProviders.of(this, factory)
                .get(ProdutoViewModel.class);

        mBinding.setProdutoViewModel(model);

        subscribeToModel(model);
    }

    private void subscribeToModel(final ProdutoViewModel model) {


        model.getObservableProduto().observe(this, new Observer<Produto>() {
            @Override
            public void onChanged(@Nullable Produto produto) {
                model.setProduto(produto);
            }
        });


    }

    /** Creates Cliente fragment for specific product ID */
    public static ProdutoFragment forProduto(int produtoId) {
        ProdutoFragment fragment = new ProdutoFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_PRODUCT_ID, produtoId);
        fragment.setArguments(args);
        return fragment;
    }
}
