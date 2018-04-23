package com.example.mathe_000.lp3projetolive;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.example.mathe_000.lp3projetolive.databinding.PagamentoItemBinding;
import com.example.mathe_000.lp3projetolive.db.Entidades.Pagamentos;

import java.util.List;

public class PagamentoAdapter extends RecyclerView.Adapter<PagamentoAdapter.PagamentoViewHolder> {

    List<? extends Pagamentos> mPagamentosList;

    @Nullable
    private final PagamentoClickCallback mPagamentoClickCallback;

    public PagamentoAdapter(@Nullable PagamentoClickCallback clickCallback) {
        mPagamentoClickCallback = clickCallback;
    }

    public void setmPagamentosList(final List<? extends Pagamentos> pagamentosList) {
        if (mPagamentosList == null) {
            mPagamentosList = pagamentosList;
            notifyItemRangeInserted(0, pagamentosList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return pagamentosList.size();
                }

                @Override
                public int getNewListSize() {
                    return pagamentosList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    if(oldItemPosition>=mPagamentosList.size()||newItemPosition>=pagamentosList.size()){
                        return false;
                    }
                    return mPagamentosList.get(oldItemPosition).getId() ==
                            pagamentosList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    if(oldItemPosition>=mPagamentosList.size()||newItemPosition>=pagamentosList.size()){
                        return false;
                    }
                    Pagamentos newPagamento = pagamentosList.get(newItemPosition);
                    Pagamentos oldPagamentos = mPagamentosList.get(oldItemPosition);
                    return newPagamento.getId() == oldPagamentos.getId()
                            && (newPagamento.getCliente().equals(newPagamento.getCliente()))
                            && (newPagamento.getProduto().equals(newPagamento.getProduto()))
                            ;
                }
            });
            mPagamentosList = pagamentosList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public PagamentoAdapter.PagamentoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PagamentoItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.pagamento_item,
                        parent, false);

        binding.setCallback(mPagamentoClickCallback);
        return new PagamentoAdapter.PagamentoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PagamentoAdapter.PagamentoViewHolder holder, int position) {
        holder.binding.setPagamento(mPagamentosList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mPagamentosList == null ? 0 : mPagamentosList.size();
    }

    static class PagamentoViewHolder extends RecyclerView.ViewHolder {

        final PagamentoItemBinding binding;

        public PagamentoViewHolder(PagamentoItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
