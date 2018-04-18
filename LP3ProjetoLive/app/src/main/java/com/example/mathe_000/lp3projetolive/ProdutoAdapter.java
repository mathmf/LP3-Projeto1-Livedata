package com.example.mathe_000.lp3projetolive;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;



import com.example.mathe_000.lp3projetolive.db.Entidades.Produto;
import com.example.mathe_000.lp3projetolive.databinding.ProdutoItemBinding;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {

    List<? extends Produto> mProdutosList;

    @Nullable
    private final ProdutoClickCallback mProdutoClickCallback;

    public ProdutoAdapter(@Nullable ProdutoClickCallback clickCallback) {
        mProdutoClickCallback = clickCallback;
    }

    public void setProdutoList(final List<? extends Produto> produtoList) {
        if (mProdutosList == null) {
            mProdutosList = produtoList;
            notifyItemRangeInserted(0, produtoList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return produtoList.size();
                }

                @Override
                public int getNewListSize() {
                    return produtoList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    if(oldItemPosition>= mProdutosList.size()||newItemPosition>=produtoList.size()){
                        return false;
                    }
                    return mProdutosList.get(oldItemPosition).getId() ==
                            produtoList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    if(oldItemPosition>= mProdutosList.size()||newItemPosition>=produtoList.size()){
                        return false;
                    }
                    Produto newProduto = produtoList.get(newItemPosition);
                    Produto oldProduto = mProdutosList.get(oldItemPosition);
                    return newProduto.getId() == oldProduto.getId()
                            && (newProduto.getNome().equals(oldProduto.getNome()))
                            && (newProduto.getPreco() == oldProduto.getPreco())
                            && (newProduto.getDesc().equals(oldProduto.getDesc()))
                            ;
                }
            });
            mProdutosList = produtoList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public ProdutoAdapter.ProdutoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProdutoItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.produto_item,
                        parent, false);

        binding.setCallback(mProdutoClickCallback);
        return new ProdutoAdapter.ProdutoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ProdutoAdapter.ProdutoViewHolder holder, int position) {
        holder.binding.setProduto(mProdutosList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mProdutosList == null ? 0 : mProdutosList.size();
    }

    static class ProdutoViewHolder extends RecyclerView.ViewHolder {

        final ProdutoItemBinding binding;

        public ProdutoViewHolder(ProdutoItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

}
